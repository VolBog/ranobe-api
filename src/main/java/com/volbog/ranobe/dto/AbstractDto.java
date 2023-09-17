package com.volbog.ranobe.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDto<E> {

  private Date createdAt;

  private Date lastModifiedAt;

  private String createdBy;

  private String lastModifiedBy;


}
