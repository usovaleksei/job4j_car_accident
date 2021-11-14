package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//@Repository
public class AccidentMem implements AccidentRepository {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> types = new HashMap<>();
    private final HashMap<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }

    @Override
    public void save(Accident accident) {

    }

    @Override
    public List<Accident> getAll() {
        return null;
    }

    public void createAccident(Accident accident) {
        if (accident.getId() == 0) {
            int id = this.counter.incrementAndGet();
            accident.setId(id);
        }
        this.accidents.put(accident.getId(), accident);
    }

    public Accident findAccidentById(int id) {
        return this.accidents.get(id);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return new ArrayList<>(this.types.values());
    }

    public AccidentType findAccidentTypeById(int id) {
        return this.types.get(id);
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(rules.values());
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }

    public Set<Rule> getRulesForAccident(List<Integer> ids) {
        Set<Rule> rules = new LinkedHashSet<>();
        for (Integer id : ids) {
            rules.add(findRuleById(id));
        }
        return rules;
    }
}
