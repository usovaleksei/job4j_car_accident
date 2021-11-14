package ru.job4j.caraccident.service;

import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.util.List;
import java.util.Set;

public interface AccidentService {

    List<Accident> getAllAccidents();

    void saveAccident(Accident accident);

    Accident findAccidentById(int id);

    List<AccidentType> getAllAccidentTypes();

    AccidentType findAccidentTypeById(int id);

    List<Rule> getAllRules();

    Set<Rule> getRulesForAccident(String[] ids);
}
