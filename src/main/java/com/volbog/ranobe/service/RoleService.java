package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.RoleDto;
import com.volbog.ranobe.entity.Role;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.RoleMapper;
import com.volbog.ranobe.repository.RoleRepository;
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
public class RoleService {

  private final RoleRepository repository;
  private final RoleMapper roleMapper;

  public RoleService(RoleRepository repository, RoleMapper roleMapper) {
    this.repository = repository;
    this.roleMapper = roleMapper;
  }

  public RoleDto save(RoleDto roleDto) {
    Role entity = roleMapper.toEntity(roleDto);
    return roleMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public RoleDto findById(Long id) {
    return roleMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<RoleDto> findByCondition(RoleDto roleDto, Pageable pageable) {
    Page<Role> entityPage = repository.findAll(pageable);
    List<Role> entities = entityPage.getContent();
    return new PageImpl<>(roleMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public RoleDto update(RoleDto roleDto, Long id) {
    RoleDto data = findById(id);
    Role entity = roleMapper.toEntity(roleDto);
    BeanUtils.copyProperties(data, entity);
    return save(roleMapper.toDto(entity));
  }
}
