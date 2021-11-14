package ru.job4j.caraccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.caraccident.repository.AccidentJdbcTemplate;

@Controller
public class IndexControl {
    private final AccidentJdbcTemplate accidents;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String showAllAccidents(Model model) {
        model.addAttribute("accidents", this.accidents.getAll());
        return "index";
    }
}
