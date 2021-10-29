package ru.job4j.caraccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = new ArrayList<>();
        model.addAttribute("user", "Aleksei Usov");

        for (int s = 1; s <= 5; s++) {
            list.add("user " + s);
        }
        model.addAttribute("strings", list);
        return "index";
    }
}
