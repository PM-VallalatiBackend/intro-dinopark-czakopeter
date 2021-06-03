package hu.progmasters.dinopark.domain;

public class Dinosaur {

    private Integer id;
    private String name;
    private String breed;
    private Diet diet;

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

    public Diet getDiet() {
        return diet;
    }

    public Dinosaur setDiet(Diet diet) {
        this.diet = diet;
        return this;
    }
}
