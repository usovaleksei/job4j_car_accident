package ru.job4j.caraccident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.IntStream;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> accidents.put(i,
                        new Accident(i, "Name " + i, "Text " + i, "Address " + i)));
    }

    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }
}
