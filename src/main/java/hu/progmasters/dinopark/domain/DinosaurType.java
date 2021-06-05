package hu.progmasters.dinopark.domain;

import java.util.Arrays;

public enum DinosaurType {
    HERBIVORE, CARNIVORE;

    public static boolean contains(String type) {
        return Arrays.stream(DinosaurType.values())
                .anyMatch(dinosaurType -> dinosaurType.name().equalsIgnoreCase(type));
    }
}
