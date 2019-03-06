package com.github.daggerok.event.mytest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyTestEventRepository extends JpaRepository<MyTestEvent, Long> {}
