package main.java.ssss.Entity;

import main.java.ssss.BFS;
import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, int speed, int healthPoints) {
        super(coordinates, speed, healthPoints);
    }



    @Override
    public void makeMove(Card card) {
        List<Coordinates> allMoves = BFS.findAllValidMoves(this, card);
        if (!allMoves.isEmpty()) {
            Coordinates coordinatesToMove = allMoves.removeFirst();
            card.changePosition(this.coordinates, coordinatesToMove);
            if (allMoves.isEmpty()) {
                this.healthPointsUp(20);
                Grass.counter--;
            } else {
                this.healthPointsDown();
            }
        }
    }


}
