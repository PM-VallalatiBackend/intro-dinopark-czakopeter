package hu.progmasters.dinopark.dto;

public class VisitorCreate {

    private String name;
    private String preferredDinoType;

    public VisitorCreate() {}

    public String getName() {
        return name;
    }

    public VisitorCreate setName(String name) {
        this.name = name;
        return this;
    }

    public String getPreferredDinoType() {
        return preferredDinoType;
    }

    public VisitorCreate setPreferredDinoType(String preferredDinoType) {
        this.preferredDinoType = preferredDinoType;
        return this;
    }
}
