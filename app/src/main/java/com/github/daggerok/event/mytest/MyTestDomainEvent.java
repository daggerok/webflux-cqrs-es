package com.github.daggerok.event.mytest;

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

import static lombok.AccessLevel.PROTECTED;

// read more:
// http://blog.netgloo.com/2014/12/18/handling-entities-inheritance-with-spring-data-jpa/
// https://github.com/netgloo/spring-boot-samples/tree/master/spring-boot-springdatajpa-inheritance/src/main/java/netgloo/models

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MyTestDomainEvent<E> implements Serializable {

  private static final long serialVersionUID = 6498118047676944890L;

  @Id
  @GeneratedValue
  @Setter(PROTECTED)
  @Column(updatable = false, nullable = false)
  protected Long id;
  //protected UUID id;

  @Version
  @Column(name = "version")
  protected Integer version;

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:s")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected LocalDateTime createdDate;

  @LastModifiedDate
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSS")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected LocalDateTime modifiedDate;

  public abstract E getData();
}
