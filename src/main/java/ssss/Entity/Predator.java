package main.java.ssss.Entity;

import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates.Coordinates;



public class Predator extends Creature implements Attacker {
    private final int strength;
    private static final int HEALTH_POINTS_GAINED_FROM_FOOD = 80;

    public Predator(Coordinates coordinates, int speed, int healthPoints, int strength) {
        super(coordinates, speed, healthPoints);
        this.strength = strength;
    }

    @Override
    public void handleTargetLocation(Coordinates coordinates, Card card) {
        Creature attackedHerbivore = (Creature) card.getEntity(coordinates);
        if (attackedHerbivore.getHealthPoints() - this.strength <= 0) {
            this.healthPointsUp(HEALTH_POINTS_GAINED_FROM_FOOD);
            card.changePosition(this.getCoordinates(), coordinates);
        } else {
            attack(attackedHerbivore);
        }
    }

    @Override
    public boolean isFood(Entity entity) {
        return entity instanceof Herbivore;
    }

    @Override
    public void attack(Creature creature) {
        creature.setHealthPoints(creature.getHealthPoints() - this.strength);
    }
}
