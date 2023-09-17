package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.NovelDto;
import com.volbog.ranobe.entity.Novel;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.NovelMapper;
import com.volbog.ranobe.repository.NovelRepository;
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
public class NovelService {

  private final NovelRepository repository;
  private final NovelMapper novelMapper;

  public NovelService(NovelRepository repository, NovelMapper novelMapper) {
    this.repository = repository;
    this.novelMapper = novelMapper;
  }

  public NovelDto save(NovelDto novelDto) {
    Novel entity = novelMapper.toEntity(novelDto);
    return novelMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public NovelDto findById(Long id) {
    return novelMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<NovelDto> findByCondition(NovelDto novelDto, Pageable pageable) {
    Page<Novel> entityPage = repository.findAll(pageable);
    List<Novel> entities = entityPage.getContent();
    return new PageImpl<>(novelMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public NovelDto update(NovelDto novelDto, Long id) {
    NovelDto data = findById(id);
    Novel entity = novelMapper.toEntity(novelDto);
    BeanUtils.copyProperties(data, entity);
    return save(novelMapper.toDto(entity));
  }
}
