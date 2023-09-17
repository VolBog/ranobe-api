package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.TagDto;
import com.volbog.ranobe.entity.Tag;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.TagMapper;
import com.volbog.ranobe.repository.TagRepository;
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
public class TagService {

  private final TagRepository repository;
  private final TagMapper tagMapper;

  public TagService(TagRepository repository, TagMapper tagMapper) {
    this.repository = repository;
    this.tagMapper = tagMapper;
  }

  public TagDto save(TagDto tagDto) {
    Tag entity = tagMapper.toEntity(tagDto);
    return tagMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public TagDto findById(Long id) {
    return tagMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<TagDto> findByCondition(TagDto tagDto, Pageable pageable) {
    Page<Tag> entityPage = repository.findAll(pageable);
    List<Tag> entities = entityPage.getContent();
    return new PageImpl<>(tagMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public TagDto update(TagDto tagDto, Long id) {
    TagDto data = findById(id);
    Tag entity = tagMapper.toEntity(tagDto);
    BeanUtils.copyProperties(data, entity);
    return save(tagMapper.toDto(entity));
  }
}
