package com.github.daggerok.event.mytest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = PROTECTED)
public class MyTestEvent extends MyTestDomainEvent<MyTestEventData> {

  @Convert(converter = MyTestEventDataJsonConverter.class)
  MyTestEventData data;
}
