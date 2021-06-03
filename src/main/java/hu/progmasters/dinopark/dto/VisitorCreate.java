package hu.progmasters.dinopark.dto;

public class VisitorCreate {

    private String name;
    private String preferredDinoDietType;

    public VisitorCreate() {}

    public String getName() {
        return name;
    }

    public VisitorCreate setName(String name) {
        this.name = name;
        return this;
    }

    public String getPreferredDinoDietType() {
        return preferredDinoDietType;
    }

    public VisitorCreate setPreferredDinoDietType(String preferredDinoDietType) {
        this.preferredDinoDietType = preferredDinoDietType;
        return this;
    }
}
