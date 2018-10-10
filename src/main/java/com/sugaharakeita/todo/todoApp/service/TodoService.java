package com.sugaharakeita.todo.todoApp.service;


import com.sugaharakeita.todo.todoApp.entity.Todo;
import com.sugaharakeita.todo.todoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public List<Todo> search(String name) {
        return todoRepository.findByNameContainsAndStatusIsFalse(name);
    }

    public Todo findByName(String name) {
        return todoRepository.findByName(name);
    }
}
