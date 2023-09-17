package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.TagDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.TagService;
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

@RequestMapping("/tag")
@RestController
@Slf4j
@Tag(name = "tag")
public class TagController {

  private final TagService tagService;

  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated TagDto tagDto) {
    tagService.save(tagDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TagDto> findById(@PathVariable("id") Long id) {
    TagDto tag = tagService.findById(id);
    return ResponseEntity.ok(tag);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(tagService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    tagService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<TagDto>> pageQuery(TagDto tagDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<TagDto> tagPage = tagService.findByCondition(tagDto, pageable);
    return ResponseEntity.ok(tagPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated TagDto tagDto,
      @PathVariable("id") Long id) {
    tagService.update(tagDto, id);
    return ResponseEntity.ok().build();
  }
}
