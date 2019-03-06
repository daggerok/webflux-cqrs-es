package com.github.daggerok.event.mytest;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyTestEventStore {
  final MyTestEventRepository domainEvents;

  @Transactional
  @PostMapping("/mytest")
  public MyTestEvent post(@RequestBody MyTestEventData request) {
    return domainEvents.save(MyTestEvent.of(
        MyTestEventData.of(request.getValue1(),
                           request.getValue2())));
  }
}
