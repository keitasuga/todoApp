package com.sugaharakeita.todo.todoApp.controller;

import com.sugaharakeita.todo.todoApp.entity.Todo;
import com.sugaharakeita.todo.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/")
    public String home(Model model) {
        Todo todo = new Todo();
        model.addAttribute("todo", todo);
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "main";
    }

    @PostMapping("/")
    public String create(Model model, @Valid @ModelAttribute Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Todo> todos = todoService.findAll();
            model.addAttribute("todos", todos);
            return "main";
        } else {
            todoService.save(todo);
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Todo> todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "edit";
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            todo.setId(id);
            todoService.save(todo);
            return "redirect:/";
        }
    }

}