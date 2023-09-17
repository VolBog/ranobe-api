package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.GlossaryTermDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.GlossaryTermService;
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

@RequestMapping("/glossary-term")
@RestController
@Slf4j
@Tag(name = "glossary-term")
public class GlossaryTermController {

  private final GlossaryTermService glossaryTermService;

  public GlossaryTermController(GlossaryTermService glossaryTermService) {
    this.glossaryTermService = glossaryTermService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated GlossaryTermDto glossaryTermDto) {
    glossaryTermService.save(glossaryTermDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<GlossaryTermDto> findById(@PathVariable("id") Long id) {
    GlossaryTermDto glossaryTerm = glossaryTermService.findById(id);
    return ResponseEntity.ok(glossaryTerm);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(glossaryTermService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    glossaryTermService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<GlossaryTermDto>> pageQuery(GlossaryTermDto glossaryTermDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<GlossaryTermDto> glossaryTermPage = glossaryTermService.findByCondition(glossaryTermDto,
        pageable);
    return ResponseEntity.ok(glossaryTermPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated GlossaryTermDto glossaryTermDto,
      @PathVariable("id") Long id) {
    glossaryTermService.update(glossaryTermDto, id);
    return ResponseEntity.ok().build();
  }
}
