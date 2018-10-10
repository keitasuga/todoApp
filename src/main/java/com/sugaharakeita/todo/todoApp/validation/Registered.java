package com.sugaharakeita.todo.todoApp.validation;

import javax.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = {RegisteredValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Registered {
    String message() default "すでに登録済みのTodo名です";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

