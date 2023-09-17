package com.volbog.ranobe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
public class NovelDto extends AbstractDto<Long> {

  private Long id;
  private String title;
  private String author;
  private String url;
  private String imageUrl;
  private float rating;
  private int totalVotes;
  private String language;
  private String artist;
  private int releaseYear;
  private String status;
  private String publisher;
  @Size(max = 10000)
  private String description;
  private byte[] imageData;
}