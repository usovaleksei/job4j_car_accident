package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.AccidentMem;

import java.util.Collection;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public Collection<Accident> getAllAccidents() {
        return store.getAllAccidents();
    }

    public void saveAccident(Accident accident) {
        store.createAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return store.findAccidentById(id);
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return store.getAllAccidentTypes();
    }

    public AccidentType findAccidentTypeById(int id) {
        return store.findAccidentTypeById(id);
    }

    public Collection<Rule> getAllRules() {
        return store.findAllRules();
    }

    public Set<Rule> getRulesForAccident(String[] ids) {
        return store.getRulesForAccident(ids);
    }
}
