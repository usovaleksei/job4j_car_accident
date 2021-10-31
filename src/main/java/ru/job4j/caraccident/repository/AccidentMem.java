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
        accidents.put(counter.incrementAndGet(), new Accident(counter.get(), "Accident one", "text one", "Moscow"));
        accidents.put(counter.incrementAndGet(), new Accident(counter.get(), "Accident two", "text two", "Moscow"));
        accidents.put(counter.incrementAndGet(), new Accident(counter.get(), "Accident three", "text three", "Moscow"));
        accidents.put(counter.incrementAndGet(), new Accident(counter.get(), "Accident four", "text four", "Moscow"));
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
}
