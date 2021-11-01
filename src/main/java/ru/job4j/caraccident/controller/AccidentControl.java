package ru.job4j.caraccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", this.accidents.getAllAccidentTypes());
        model.addAttribute("rules", this.accidents.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("typeId") String typeId, HttpServletRequest req) {
        accident.setType(accidents.findAccidentTypeById(Integer.parseInt(typeId)));
        this.accidents.createAccident(accident);
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(accidents.getRulesForAccident(ids));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = this.accidents.findAccidentById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", this.accidents.getAllAccidentTypes());
        model.addAttribute("rules", this.accidents.findAllRules());
        return "accident/update";
    }
}
