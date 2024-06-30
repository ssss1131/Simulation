package main.java.ssss.Entity;

import main.java.ssss.Coordinates;

public class Grass extends Entity{
    public static int counter;

    public Grass(Coordinates coordinates) {
        super(coordinates);
        counter++;
    }

}
