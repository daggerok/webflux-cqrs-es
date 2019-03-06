package com.github.daggerok.event.noinheritance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
//@Table(name = "domain_event")
@Inheritance//(strategy = SINGLE_TABLE)
@EntityListeners(AuditingEntityListener.class)
public abstract class DomainEvent<E> implements Serializable {

  private static final long serialVersionUID = 1238208047426944895L;

  public abstract E getData();

  @Id
  @GeneratedValue
  @Setter(PROTECTED)
  @Column(updatable = false, nullable = false)
  protected Long id;
  //protected UUID id;
  //@Version
  //@Column(name = "version")
  //protected Integer version;
  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:s")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected LocalDateTime createdDate;

  @LastModifiedDate
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSS")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected LocalDateTime modifiedDate;
}
