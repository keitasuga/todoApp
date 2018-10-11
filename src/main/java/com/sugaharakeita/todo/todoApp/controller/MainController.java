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
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        if (bindingResult.hasErrors()) {
            return "main";
        }

        Optional<Todo> todoOpt = todoService.findByName(todo.getName());
        if (!todoOpt.isPresent()) {
            todoService.create(todo);
            return "redirect:/";
        } else {
            model.addAttribute("message", "すでに登録済みのTodo名があります");
            return "main";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Todo> todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @GetMapping(value = "/search")
    public String search(Model model) {
        return "search";
    }

    @GetMapping(value = "/search/result")
    public String search(Model model, @RequestParam("name") String name) {
        List<Todo> results = todoService.search(name);
        if (results.size() > 0) {
            model.addAttribute("message", "ToDoが" + results.size() + "件見つかりました");
            model.addAttribute("results", results);
            return "search";
        } else {
            model.addAttribute("message", "対象のToDoは見つかりません");
            return "search";
        }
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Todo todo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        Optional<Todo> todoOpt = todoService.findById(id);
        if (!todoOpt.isPresent()) {
            return "/error/404";
        }

        Optional<Todo> todoOptName = todoService.findByName(todo.getName());
        if (!todoOptName.isPresent()) {
            todo.setId(id);
            todoService.update(todo);
            return "redirect:/";
        }
        Todo target = todoOptName.get();
        if (id.equals(target.getId())) {
            todo.setId(id);
            todoService.update(todo);
            return "redirect:/";
        } else {
            model.addAttribute("message", "すでに登録済みのTodo名があります");
            return "edit";
        }
    }

    @PostMapping("/{id}/finish")
    public String finish(@PathVariable Long id, @RequestParam("status") Integer status) {
        Optional<Todo> todoOpt = todoService.findById(id);
        if (!todoOpt.isPresent()) {
            return "/error/404";
        }

        Todo target = todoOpt.get();
        target.setStatus(1D == status);

        todoService.update(target);

        return "redirect:/";
    }
}