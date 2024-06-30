package main.java.ssss.Entity;

import main.java.ssss.Coordinates;

abstract public class Entity {
    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " at coordinates: " + coordinates;
    }
}
