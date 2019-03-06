package com.github.daggerok.event.noinheritance;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@Setter(PROTECTED)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = PROTECTED)
public class Event2 extends DomainEvent<EventData> {

  @Convert(converter = EventDataJsonConverter.class)
  EventData data;

  String field21;
  String field22;
}
