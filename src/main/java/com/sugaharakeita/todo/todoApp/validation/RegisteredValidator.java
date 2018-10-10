package com.sugaharakeita.todo.todoApp.validation;

import com.sugaharakeita.todo.todoApp.entity.Todo;
import com.sugaharakeita.todo.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.*;

public class RegisteredValidator implements ConstraintValidator<Registered, String> {

    @Autowired
    TodoService todoService;

    @Override
    public void initialize(Registered constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Todo todo = todoService.findByName(value);
        if (todo == null) {
            return true;
        }
        return false;
    }
}
