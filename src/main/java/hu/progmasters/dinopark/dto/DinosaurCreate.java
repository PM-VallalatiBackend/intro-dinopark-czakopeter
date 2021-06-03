package hu.progmasters.dinopark.dto;

public class DinosaurCreate {

    private String name;
    private String breed;
    private String diet;

    public DinosaurCreate() {}

    public String getName() {
        return name;
    }

    public DinosaurCreate setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public DinosaurCreate setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public String getDiet() {
        return diet;
    }

    public DinosaurCreate setDiet(String diet) {
        this.diet = diet;
        return this;
    }
}
