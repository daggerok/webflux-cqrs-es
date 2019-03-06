package com.github.daggerok.event;

import com.github.daggerok.event.mytest.MyTestEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class DomainEventTest {

  @Autowired
  MyTestEventRepository myEventRepository;

  @Test
  void test() {
    assertThat(myEventRepository.findAll()).isNotNull();
  }
}
