package com.volbog.ranobe.service;

import com.volbog.ranobe.dto.GenreDto;
import com.volbog.ranobe.entity.Genre;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.mapper.GenreMapper;
import com.volbog.ranobe.repository.GenreRepository;
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
public class GenreService {

  private final GenreRepository repository;
  private final GenreMapper genreMapper;

  public GenreService(GenreRepository repository, GenreMapper genreMapper) {
    this.repository = repository;
    this.genreMapper = genreMapper;
  }

  public GenreDto save(GenreDto genreDto) {
    Genre entity = genreMapper.toEntity(genreDto);
    return genreMapper.toDto(repository.save(entity));
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public GenreDto findById(Long id) {
    return genreMapper.toDto(repository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
  }

  public Page<GenreDto> findByCondition(GenreDto genreDto, Pageable pageable) {
    Page<Genre> entityPage = repository.findAll(pageable);
    List<Genre> entities = entityPage.getContent();
    return new PageImpl<>(genreMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public GenreDto update(GenreDto genreDto, Long id) {
    GenreDto data = findById(id);
    Genre entity = genreMapper.toEntity(genreDto);
    BeanUtils.copyProperties(data, entity);
    return save(genreMapper.toDto(entity));
  }
}
