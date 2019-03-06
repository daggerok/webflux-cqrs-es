package com.github.daggerok.event.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class EventDataJsonConverter implements AttributeConverter<EventData, String> {

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(EventData attribute) {
    return Try.of(() -> objectMapper.writeValueAsString(attribute))
              .getOrElseGet(throwable -> "{}");
  }

  @Override
  public EventData convertToEntityAttribute(String dbData) {
    return Try.of(() -> objectMapper.readValue(dbData, EventData.class))
              .getOrElseGet(throwable -> null);
  }
}
