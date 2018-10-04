package com.sugaharakeita.todo.todoApp.controller;

import com.sugaharakeita.todo.todoApp.entity.Todo;
import com.sugaharakeita.todo.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public String home(Model model) {
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "main";
    }

    @PostMapping("/")
    public String create(@ModelAttribute Todo todo) {
        todoService.save(todo);
        return "redirect:/";
    }

//    @GetMapping("{id}/edit")
//    public String edit(@PathVariable Long id, Model model) {
//        Todo todo = todoService.findOne(id);
//        model.addAttribute("todo", todo);
//        return "edit";
//    }


//    @PutMapping("{id}")
//    public String update(@PathVariable Long id, @ModelAttribute Todo todo) {
//        todo.setId(id);
//        todoService.save(todo);
//        return "redirect:/";
//    }


}
