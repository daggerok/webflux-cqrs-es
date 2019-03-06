package com.github.daggerok.event.mytest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Data
@Setter(PROTECTED)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class MyTestEventData {
  String value1 = "default value 1";
  String value2;
}
