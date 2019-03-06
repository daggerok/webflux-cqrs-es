package com.github.daggerok.event.noinheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class MyTestEventDataJsonConverter implements AttributeConverter<MyTestEventData, String> {

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(MyTestEventData attribute) {
    return Try.of(() -> objectMapper.writeValueAsString(attribute))
              .getOrElseGet(throwable -> "{}");
  }

  @Override
  public MyTestEventData convertToEntityAttribute(String dbData) {
    return Try.of(() -> objectMapper.readValue(dbData, MyTestEventData.class))
              .getOrElseGet(throwable -> null);
  }
}
