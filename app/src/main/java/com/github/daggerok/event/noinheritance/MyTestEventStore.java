package com.github.daggerok.event.noinheritance;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyTestEventStore {
  final MyTestEventRepository domainEvents;

  @Transactional
  @PostMapping("/inheritance")
  public MyTestEvent post(@RequestBody MyTestEventData request) {
    return domainEvents.save(MyTestEvent.of(
        MyTestEventData.of(request.getValue1(),
                           request.getValue2())));
  }

  @Transactional
  @PostMapping("/inheritance/{id}") // test if version is updating...
  public MyTestEvent post(@PathVariable("id") Long id, @RequestBody MyTestEventData request) {
    MyTestEvent event = domainEvents.findById(id)
                                    .orElseThrow(RuntimeException::new);
    event.setData(MyTestEventData.of(request.getValue1(),
                                     request.getValue2()));
    return domainEvents.save(event);
  }
}
