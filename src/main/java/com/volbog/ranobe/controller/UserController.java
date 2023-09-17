package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.UserDto;
import com.volbog.ranobe.exception.ResourceNotFoundException;
import com.volbog.ranobe.service.UserService;
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

@RequestMapping("/user")
@RestController
@Slf4j
@Tag(name = "user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated UserDto userDto) {
    userService.save(userDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
    UserDto user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(userService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data!");
      return new ResourceNotFoundException();
    });
    userService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<UserDto>> pageQuery(UserDto userDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<UserDto> userPage = userService.findByCondition(userDto, pageable);
    return ResponseEntity.ok(userPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated UserDto userDto,
      @PathVariable("id") Long id) {
    userService.update(userDto, id);
    return ResponseEntity.ok().build();
  }
}
