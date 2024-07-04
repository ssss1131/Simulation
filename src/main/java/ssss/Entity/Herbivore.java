package main.java.ssss.Entity;

import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates.Coordinates;



public class Herbivore extends Creature {
    private static final int HEALTH_POINTS_GAINED_FROM_GRASS = 20;

    public Herbivore(Coordinates coordinates, int speed, int healthPoints) {
        super(coordinates, speed, healthPoints);
    }

    @Override
    public boolean isFood(Entity entity) {
        return entity instanceof Grass;
    }

    @Override
    public void handleTargetLocation(Coordinates coordinatesToMove, Card card) {
        card.changePosition(this.getCoordinates(),coordinatesToMove);
        this.healthPointsUp(HEALTH_POINTS_GAINED_FROM_GRASS);
    }


}
