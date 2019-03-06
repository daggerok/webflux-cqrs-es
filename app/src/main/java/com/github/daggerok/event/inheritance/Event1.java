package com.github.daggerok.event.inheritance;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@Setter(PROTECTED)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = PROTECTED)
public class Event1 extends DomainEvent<EventData> {

  @Convert(converter = EventDataJsonConverter.class)
  EventData data;

  String dataFieldOfEvent1;
}
