package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.UserDto;
import com.volbog.ranobe.entity.User;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.UserMapper;
import com.volbog.ranobe.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class UserService {

  private final UserRepository repository;
  private final UserMapper userMapper;

  public UserService(UserRepository repository, UserMapper userMapper) {
    this.repository = repository;
    this.userMapper = userMapper;
  }

  public UserDto save(UserDto userDto) {
    User entity = userMapper.toEntity(userDto);
    return userMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public UserDto findById(Long id) {
    return userMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<UserDto> findByCondition(UserDto userDto, Pageable pageable) {
    Page<User> entityPage = repository.findAll(pageable);
    List<User> entities = entityPage.getContent();
    return new PageImpl<>(userMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public UserDto update(UserDto userDto, Long id) {
    UserDto data = findById(id);
    User entity = userMapper.toEntity(userDto);
    BeanUtils.copyProperties(data, entity);
    return save(userMapper.toDto(entity));
  }
}
