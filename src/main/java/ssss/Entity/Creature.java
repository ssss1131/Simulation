package main.java.ssss.Entity;

import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates;
import main.java.ssss.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

abstract public class Creature extends Entity{
    private final int speed;
    private int healthPoints;
    public int initialHealthPoints;

    public Creature(Coordinates coordinates, int speed, int healthPoints) {
        super(coordinates);
        this.speed = speed;
        this.healthPoints = healthPoints;
        initialHealthPoints = healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    abstract public void makeMove(Card card);


    public boolean isFood(Entity entity) {
        if (this instanceof Herbivore) {
            return entity instanceof Grass;
        } else if (this instanceof Predator) {
            return entity instanceof Herbivore;
        }
        return false;
    }


    public Set<CoordinatesShift> getAllPossibleMoves(){
        Set<CoordinatesShift> result = new HashSet<>();
        for(int i = -speed; i <= speed; i++){
            if(i!=0){
                result.add(new CoordinatesShift(i,0)); // по параллели
                result.add(new CoordinatesShift(0,i)); // по меридиане
                result.add(new CoordinatesShift(i,i)); // слева вверх и справа вниз
                result.add(new CoordinatesShift(-i,i)); // справа вверх и слева вниз
            }
        }
        return result;
    }

    public void healthPointsUp(int hp){
        this.setHealthPoints(getHealthPoints() + hp);
    }

    public void healthPointsDown(){
        this.setHealthPoints(getHealthPoints() - 15);
    }

    public void attacked(int strength){
        this.setHealthPoints(getHealthPoints() - strength);
    }


}
