package com.github.daggerok.event.noinheritance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyTestEventRepository extends JpaRepository<MyTestEvent, Long> {}
