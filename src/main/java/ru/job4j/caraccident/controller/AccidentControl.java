package ru.job4j.caraccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.repository.AccidentMem;
import ru.job4j.caraccident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", this.service.getAllAccidentTypes());
        model.addAttribute("rules", this.service.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("typeId") String typeId, HttpServletRequest req) {
        accident.setType(service.findAccidentTypeById(Integer.parseInt(typeId)));
        this.service.saveAccident(accident);
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(service.getRulesForAccident(ids));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = this.service.findAccidentById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", this.service.getAllAccidentTypes());
        model.addAttribute("rules", this.service.getAllRules());
        return "accident/update";
    }
}
