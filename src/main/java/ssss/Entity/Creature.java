package main.java.ssss.Entity;

import main.java.ssss.Action.BFS;
import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates.Coordinates;

import java.util.List;

abstract public class Creature extends Entity{
    private final int speed;
    private int healthPoints;
    public int initialHealthPoints;
    private static final int HEALTH_POINTS_DOWN_FROM_MOVE = 15;

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

     public void makeMove(Card card)
    {
        List<Coordinates> allMoves = BFS.findAllValidMoves(this, card);
        if(!allMoves.isEmpty()){
            Coordinates coordinatesToMove = allMoves.removeFirst();
            if(isFood(card.getEntity(coordinatesToMove))){
                handleTargetLocation(coordinatesToMove, card);
            }else{
                card.changePosition(this.getCoordinates(),coordinatesToMove);
                healthPointsDown();
            }
        }

    }

    abstract public void handleTargetLocation(Coordinates coordinatesToMove, Card card);

    abstract public boolean isFood(Entity entity);


    public void healthPointsUp(int hp){
        this.setHealthPoints(getHealthPoints() + hp);
    }

    public void healthPointsDown(){
        this.setHealthPoints(getHealthPoints() - HEALTH_POINTS_DOWN_FROM_MOVE);
    }

     public int getSpeed() {
        return speed;
    }
}
