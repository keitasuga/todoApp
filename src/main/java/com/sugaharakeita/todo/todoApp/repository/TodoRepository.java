package com.sugaharakeita.todo.todoApp.repository;


import com.sugaharakeita.todo.todoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByNameContainsAndStatusIsFalse(String name);

    Todo findByName(String name);
}
