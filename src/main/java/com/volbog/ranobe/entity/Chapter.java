package com.volbog.ranobe.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chapter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private int chapterNumber;

  @ManyToOne
  private Novel novel;

  @OneToOne(mappedBy = "chapter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Translation translation;

  private LocalDateTime createAt;

  private LocalDateTime lastModifiedAt;

  private String createdBy;

  private String lastModifiedBy;
}