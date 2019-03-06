package com.github.daggerok.event.noinheritance;

import io.vavr.collection.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@NoRepositoryBean
//public interface BaseDomainEventRepository<T extends DomainEvent<EventData>> extends Repository<T, Long> {
interface BaseDomainEventRepository<T extends DomainEvent<EventData>> extends JpaRepository<T, Long> {
  //T findBySomethingCommon(...);
  //List<T> findAny();
}

@Transactional
interface DomainEventRepository extends BaseDomainEventRepository<DomainEvent<EventData>> {}

@Transactional
interface Event1Repository extends BaseDomainEventRepository<Event1> {}

@Transactional
interface Event2Repository extends BaseDomainEventRepository<Event2> {}

@Transactional
interface Event3Repository extends BaseDomainEventRepository<Event3> {}

@RestController
@RequiredArgsConstructor
public class Repositories {

  final DomainEventRepository domainEvents;
  final Event1Repository events1;
  final Event2Repository events2;
  final Event3Repository events3;

  @Transactional
  @PostMapping("/event1")
  void storeEvent1(@RequestBody Event1 event1) {
    events1.save(event1);
  }

  @Transactional
  @PostMapping("/event2")
  void storeEvent2(@RequestBody Event2 event2) {
    events2.save(event2);
  }

  @Transactional
  @PostMapping("/event3")
  void storeEvent3(@RequestBody Event3 event3) {
    events3.save(event3);
  }

  @GetMapping("/events")
  ResponseEntity<?> getAll() {
    return ResponseEntity.ok(
        HashMap.of("domainEvents", domainEvents.findAll(),
                   "events1", events1.findAll(),
                   "events2", events2.findAll(),
                   "events3", events3.findAll()).toJavaMap());
  }
}
