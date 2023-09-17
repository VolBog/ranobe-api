package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.RoleDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.RoleService;
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

@RequestMapping("/role")
@RestController
@Slf4j
@Tag(name = "role")
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated RoleDto roleDto) {
    roleService.save(roleDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoleDto> findById(@PathVariable("id") Long id) {
    RoleDto role = roleService.findById(id);
    return ResponseEntity.ok(role);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(roleService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    roleService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<RoleDto>> pageQuery(RoleDto roleDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<RoleDto> rolePage = roleService.findByCondition(roleDto, pageable);
    return ResponseEntity.ok(rolePage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated RoleDto roleDto,
      @PathVariable("id") Long id) {
    roleService.update(roleDto, id);
    return ResponseEntity.ok().build();
  }
}
