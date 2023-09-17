package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.ChapterDto;
import com.volbog.ranobe.entity.Chapter;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.ChapterMapper;
import com.volbog.ranobe.repository.ChapterRepository;
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
public class ChapterService {

  private final ChapterRepository repository;
  private final ChapterMapper chapterMapper;

  public ChapterService(ChapterRepository repository, ChapterMapper chapterMapper) {
    this.repository = repository;
    this.chapterMapper = chapterMapper;
  }

  public ChapterDto save(ChapterDto chapterDto) {
    Chapter entity = chapterMapper.toEntity(chapterDto);
    return chapterMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public ChapterDto findById(Long id) {
    return chapterMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<ChapterDto> findByCondition(ChapterDto chapterDto, Pageable pageable) {
    Page<Chapter> entityPage = repository.findAll(pageable);
    List<Chapter> entities = entityPage.getContent();
    return new PageImpl<>(chapterMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public ChapterDto update(ChapterDto chapterDto, Long id) {
    ChapterDto data = findById(id);
    Chapter entity = chapterMapper.toEntity(chapterDto);
    BeanUtils.copyProperties(data, entity);
    return save(chapterMapper.toDto(entity));
  }
}
