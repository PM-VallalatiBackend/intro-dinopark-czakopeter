package hu.progmasters.dinopark.domain;

import hu.progmasters.dinopark.dto.DinosaurInfo;

import java.util.List;

public class Visitor {

    private static int MIN_REQUIRED_DINO = 3;

    private Integer id;
    private String name;
    private DinosaurDietType preferred;
    private int rating;

    public Visitor() {}

    public Integer getId() {
        return id;
    }

    public Visitor setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Visitor setName(String name) {
        this.name = name;
        return this;
    }

    public DinosaurDietType getPreferred() {
        return preferred;
    }

    public Visitor setPreferred(DinosaurDietType preferred) {
        this.preferred = preferred;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Visitor setRating(List<DinosaurInfo> dinosaurs) {
        if(dinosaurs.size() < MIN_REQUIRED_DINO) {
            this.rating = 1;
        } else {
            this.rating = dinosaurs.stream()
                    .mapToInt(dino -> preferred.equals(DinosaurDietType.valueOf(dino.getDiet().toUpperCase())) ? 5 : 2)
                    .sum() / dinosaurs.size();
//            int prefNumber = (int)dinosaurs.stream()
//                    .filter(dino -> dino.getDiet().equals(preferred)).count();
//            this.rating = (prefNumber * 5 + (dinosaurs.size() - prefNumber) * 2) / dinosaurs.size();
        }
        return this;
    }
}
