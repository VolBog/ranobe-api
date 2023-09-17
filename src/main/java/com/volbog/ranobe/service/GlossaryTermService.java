package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.GlossaryTermDto;
import com.volbog.ranobe.entity.GlossaryTerm;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.GlossaryTermMapper;
import com.volbog.ranobe.repository.GlossaryTermRepository;
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
public class GlossaryTermService {

  private final GlossaryTermRepository repository;
  private final GlossaryTermMapper glossaryTermMapper;

  public GlossaryTermService(GlossaryTermRepository repository,
      GlossaryTermMapper glossaryTermMapper) {
    this.repository = repository;
    this.glossaryTermMapper = glossaryTermMapper;
  }

  public GlossaryTermDto save(GlossaryTermDto glossaryTermDto) {
    GlossaryTerm entity = glossaryTermMapper.toEntity(glossaryTermDto);
    return glossaryTermMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public GlossaryTermDto findById(Long id) {
    return glossaryTermMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<GlossaryTermDto> findByCondition(GlossaryTermDto glossaryTermDto, Pageable pageable) {
    Page<GlossaryTerm> entityPage = repository.findAll(pageable);
    List<GlossaryTerm> entities = entityPage.getContent();
    return new PageImpl<>(glossaryTermMapper.toDto(entities), pageable,
        entityPage.getTotalElements());
  }

  public GlossaryTermDto update(GlossaryTermDto glossaryTermDto, Long id) {
    GlossaryTermDto data = findById(id);
    GlossaryTerm entity = glossaryTermMapper.toEntity(glossaryTermDto);
    BeanUtils.copyProperties(data, entity);
    return save(glossaryTermMapper.toDto(entity));
  }
}
