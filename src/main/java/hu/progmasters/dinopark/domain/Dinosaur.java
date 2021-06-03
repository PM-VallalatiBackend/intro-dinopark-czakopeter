package hu.progmasters.dinopark.domain;

public class Dinosaur {

    private Integer id;
    private String name;
    private String breed;
    private DinosaurDietType dinosaurDietType;

    public Dinosaur() {}

    public Integer getId() {
        return id;
    }

    public Dinosaur setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dinosaur setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Dinosaur setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public DinosaurDietType getDiet() {
        return dinosaurDietType;
    }

    public Dinosaur setDiet(DinosaurDietType dinosaurDietType) {
        this.dinosaurDietType = dinosaurDietType;
        return this;
    }
}
