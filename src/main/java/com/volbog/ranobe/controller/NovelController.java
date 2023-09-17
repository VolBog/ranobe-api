package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.NovelDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.NovelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/novel")
@RestController
@Slf4j
@Tag(name = "novel")
public class NovelController {

  private final NovelService novelService;

  public NovelController(NovelService novelService) {
    this.novelService = novelService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated NovelDto novelDto) {
    novelService.save(novelDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<NovelDto> findById(@PathVariable("id") Long id) {
    NovelDto novel = novelService.findById(id);
    return ResponseEntity.ok(novel);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(novelService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    novelService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<NovelDto>> pageQuery(NovelDto novelDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<NovelDto> novelPage = novelService.findByCondition(novelDto, pageable);
    return ResponseEntity.ok(novelPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated NovelDto novelDto,
      @PathVariable("id") Long id) {
    novelService.update(novelDto, id);
    return ResponseEntity.ok().build();
  }
}
