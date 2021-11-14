package ru.job4j.caraccident.service;

import org.springframework.stereotype.Service;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;
import ru.job4j.caraccident.repository.AccidentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentServiceImpl(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public List<Accident> getAllAccidents() {
        return accidentRepository.getAll();
    }

    public void saveAccident(Accident accident) {
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findAccidentById(id);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return accidentRepository.getAllAccidentTypes();
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentRepository.findAccidentTypeById(id);
    }

    public List<Rule> getAllRules() {
        return accidentRepository.findAllRules();
    }

    public Set<Rule> getRulesForAccident(String[] ids) {
        List<Integer> idList = Arrays.stream(ids).map(Integer::valueOf).collect(Collectors.toList());
        return accidentRepository.getRulesForAccident(idList);
    }
}
