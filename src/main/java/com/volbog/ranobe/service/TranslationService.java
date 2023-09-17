package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.TranslationDto;
import com.volbog.ranobe.entity.Translation;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.TranslationMapper;
import com.volbog.ranobe.repository.TranslationRepository;
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
public class TranslationService {

  private final TranslationRepository repository;
  private final TranslationMapper translationMapper;

  public TranslationService(TranslationRepository repository, TranslationMapper translationMapper) {
    this.repository = repository;
    this.translationMapper = translationMapper;
  }

  public TranslationDto save(TranslationDto translationDto) {
    Translation entity = translationMapper.toEntity(translationDto);
    return translationMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public TranslationDto findById(Long id) {
    return translationMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<TranslationDto> findByCondition(TranslationDto translationDto, Pageable pageable) {
    Page<Translation> entityPage = repository.findAll(pageable);
    List<Translation> entities = entityPage.getContent();
    return new PageImpl<>(translationMapper.toDto(entities), pageable,
        entityPage.getTotalElements());
  }

  public TranslationDto update(TranslationDto translationDto, Long id) {
    TranslationDto data = findById(id);
    Translation entity = translationMapper.toEntity(translationDto);
    BeanUtils.copyProperties(data, entity);
    return save(translationMapper.toDto(entity));
  }
}
