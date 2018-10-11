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

    public Todo create(Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setName(todo.getName());
        newTodo.setLimit(todo.getLimit());
        return todoRepository.save(newTodo);
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo update(Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setId(todo.getId());
        newTodo.setName(todo.getName());
        newTodo.setLimit(todo.getLimit());
        newTodo.setStatus(todo.isStatus());
        return todoRepository.save(newTodo);
    }

    public List<Todo> search(String name) {
        return todoRepository.findByNameContainsAndStatusIsFalseOrderByIdDesc(name);
    }

    public Optional<Todo> findByName(String name) {
        return todoRepository.findByName(name);
    }

}
