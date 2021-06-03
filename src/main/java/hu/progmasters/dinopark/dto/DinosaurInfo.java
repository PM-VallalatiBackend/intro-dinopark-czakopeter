package hu.progmasters.dinopark.dto;

public class DinosaurInfo {

    private int id;
    private String name;
    private String breed;
    private String diet;

    public DinosaurInfo() {
    }

    public int getId() {
        return id;
    }

    public DinosaurInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DinosaurInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public DinosaurInfo setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public String getDiet() {
        return diet;
    }

    public DinosaurInfo setDiet(String diet) {
        this.diet = diet;
        return this;
    }
}
