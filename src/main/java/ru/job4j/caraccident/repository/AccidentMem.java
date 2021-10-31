package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();
    AtomicInteger counter = new AtomicInteger();


    public AccidentMem() {
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }

    public void createAccident(Accident accident) {
        int id = this.counter.incrementAndGet();
        accident.setId(id);
        this.accidents.put(accident.getId(), accident);
    }
}
