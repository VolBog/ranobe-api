package com.volbog.ranobe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Translation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Chapter chapter;

  @Lob
  private String originalText;

  @Lob
  private String translatedText;
  private LocalDateTime createAt;

  private LocalDateTime lastModifiedAt;

  private String createdBy;

  private String lastModifiedBy;
}
