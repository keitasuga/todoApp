package com.sugaharakeita.todo.todoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("main")
    public String main() {
        return "main";
    }

    @PostMapping("main")
    public String main(ModelMap modelMap, @RequestParam("todo") String todo, @RequestParam("limit") String limit) {
        modelMap.addAttribute("todo", todo);
        modelMap.addAttribute("limit", limit);
        return "main";
    }
}
