package com.github.daggerok.event.noinheritance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Data
@Setter(PROTECTED)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EventData {
  String val1 = "default value 1";
  String val2 = "default val 2";
  String val3 = "and 3...";
}
