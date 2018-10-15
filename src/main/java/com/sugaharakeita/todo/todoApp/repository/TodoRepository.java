package com.sugaharakeita.todo.todoApp.repository;


import com.sugaharakeita.todo.todoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByNameContainsAndStatusIsFalseOrderByCreatedAtDescIdDesc(String name);

    Optional<Todo> findByName(String name);

}
