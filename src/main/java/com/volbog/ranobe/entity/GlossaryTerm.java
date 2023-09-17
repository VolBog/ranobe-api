package com.volbog.ranobe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GlossaryTerm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String originalTerm;
  private String translatedTerm;

  @ManyToOne
  private Novel novel;
  private LocalDateTime createAt;

  private LocalDateTime lastModifiedAt;

  private String createdBy;

  private String lastModifiedBy;

}
