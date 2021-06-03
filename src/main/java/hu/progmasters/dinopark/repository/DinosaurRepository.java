package hu.progmasters.dinopark.repository;

import hu.progmasters.dinopark.domain.Diet;
import hu.progmasters.dinopark.domain.Dinosaur;

import java.util.List;

public interface DinosaurRepository {

    Dinosaur save(Dinosaur dinosaur);

    List<Dinosaur> findAll();

    List<Dinosaur> findAllByDiet(Diet diet);
}
