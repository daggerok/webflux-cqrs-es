package com.github.daggerok.event.inheritance;

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
public class Event3 extends DomainEvent<EventData> {

  @Convert(converter = EventDataJsonConverter.class)
  EventData data;

  @Column(name = "event3_data_field_1")
  String field1;

  @Column(name = "event3_data_field_2")
  String field2;

  @Column(name = "event3_data_field_3")
  String field3;
}
