package com.sugaharakeita.todo.todoApp.service;


import com.sugaharakeita.todo.todoApp.entity.Todo;
import com.sugaharakeita.todo.todoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@Transactional
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll(new Sort(new Sort.Order(DESC, "createdAt"), new Sort.Order(DESC, "Id")));
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

    public Todo update(Todo todo, Todo target) {
        target.setName(todo.getName());
        target.setLimit(todo.getLimit());
        target.setStatus(todo.isStatus());
        return todoRepository.save(target);
    }

    public Todo changeStatus(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> search(String name) {
        return todoRepository.findByNameContainsAndStatusIsFalseOrderByCreatedAtDescIdDesc(name);
    }

    public Optional<Todo> findByName(String name) {
        return todoRepository.findByName(name);
    }

}
