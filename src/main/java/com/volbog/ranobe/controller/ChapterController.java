package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.ChapterDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.ChapterService;
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

@RequestMapping("/api/chapter")
@RestController
@Slf4j
@Tag(name = "chapter")
public class ChapterController {

  private final ChapterService chapterService;

  public ChapterController(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated ChapterDto chapterDto) {
    chapterService.save(chapterDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ChapterDto> findById(@PathVariable("id") Long id) {
    ChapterDto chapter = chapterService.findById(id);
    return ResponseEntity.ok(chapter);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(chapterService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    chapterService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<ChapterDto>> pageQuery(ChapterDto chapterDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<ChapterDto> chapterPage = chapterService.findByCondition(chapterDto, pageable);
    return ResponseEntity.ok(chapterPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated ChapterDto chapterDto,
      @PathVariable("id") Long id) {
    chapterService.update(chapterDto, id);
    return ResponseEntity.ok().build();
  }
}
