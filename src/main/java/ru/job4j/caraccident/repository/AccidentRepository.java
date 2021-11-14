package ru.job4j.caraccident.repository;

import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AccidentRepository {

    void save(Accident accident);

    List<Accident> getAll();

    void createAccident(Accident accident);

    Accident findAccidentById(int id);

    List<AccidentType> getAllAccidentTypes();

    AccidentType findAccidentTypeById(int id);

    List<Rule> findAllRules();

    Rule findRuleById(int id);

    Set<Rule> getRulesForAccident(List<Integer> ids);
}
