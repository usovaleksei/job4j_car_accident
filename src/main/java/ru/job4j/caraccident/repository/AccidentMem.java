package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> types = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
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

    public Collection<AccidentType> getAllAccidentTypes() {
        return this.types.values();
    }

    public AccidentType findAccidentTypeById(int id) {
        return this.types.get(id);
    }
}
