package main.java.ssss.Entity;

import main.java.ssss.Coordinates.Coordinates;

abstract public class Entity {
    private  Coordinates coordinates;


    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
