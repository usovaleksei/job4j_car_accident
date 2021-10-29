package ru.job4j.caraccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.service.AccidentService;

import java.util.Collection;

@Controller
public class IndexControl {
    private final AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showAllAccidents(Model model) {
        Collection<Accident> accidents = service.getAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
