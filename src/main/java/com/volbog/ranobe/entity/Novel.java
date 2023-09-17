package com.volbog.ranobe.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Novel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String author;
  private String url;
  private String imageUrl;
  @Column(name = "`rating`")
  private float rating;
  private int totalVotes;
  private String language;
  private String artist;
  private int releaseYear;
  private String status;
  private String publisher;
  @Column(length = 10000)
  private String description;
  @CreatedDate
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  @LastModifiedDate
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date lastModifiedAt;
  private String createdBy;

  private String lastModifiedBy;
  @Lob
  private byte[] imageData;


  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Chapter> chapters;

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<GlossaryTerm> glossaryTerms;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "novel_genre",
      joinColumns = @JoinColumn(name = "novel_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private Set<Genre> genres;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "novel_tag",
      joinColumns = @JoinColumn(name = "novel_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags;

}
