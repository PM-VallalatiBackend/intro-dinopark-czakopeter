package hu.progmasters.dinopark.repository;

import hu.progmasters.dinopark.domain.DinosaurDietType;
import hu.progmasters.dinopark.domain.Dinosaur;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DinosaurRepositoryInMemory implements DinosaurRepository {

    private final Map<Integer, Dinosaur> dinosaurs = new HashMap<>();
    private int nextIndex = 1;

    @Override
    public Dinosaur save(Dinosaur dinosaur) {
        dinosaur.setId(nextIndex);
        dinosaurs.put(dinosaur.getId(), dinosaur);
        nextIndex++;
        return dinosaur;
    }

    @Override
    public List<Dinosaur> findAll() {
        return dinosaurs.values().stream()
                .sorted(Comparator.comparing(Dinosaur::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dinosaur> findAllByDiet(DinosaurDietType dinosaurDietType) {
        return dinosaurs.values().stream()
                .filter(dino -> dino.getDiet().equals(dinosaurDietType))
                .sorted(Comparator.comparing(Dinosaur::getId))
                .collect(Collectors.toList());
    }
}
