package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.TranslationDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.TranslationService;
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

@RequestMapping("/translation")
@RestController
@Slf4j
@Tag(name = "translation")
public class TranslationController {

  private final TranslationService translationService;

  public TranslationController(TranslationService translationService) {
    this.translationService = translationService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated TranslationDto translationDto) {
    translationService.save(translationDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TranslationDto> findById(@PathVariable("id") Long id) {
    TranslationDto translation = translationService.findById(id);
    return ResponseEntity.ok(translation);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(translationService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    translationService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<TranslationDto>> pageQuery(TranslationDto translationDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<TranslationDto> translationPage = translationService.findByCondition(translationDto,
        pageable);
    return ResponseEntity.ok(translationPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated TranslationDto translationDto,
      @PathVariable("id") Long id) {
    translationService.update(translationDto, id);
    return ResponseEntity.ok().build();
  }
}
