package main.java.ssss.Action;

import main.java.ssss.Card.Card;
import main.java.ssss.Card.CardUtils;
import main.java.ssss.Coordinates.Coordinates;
import main.java.ssss.Coordinates.CoordinatesShift;
import main.java.ssss.Entity.Creature;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS {
    public static List<Coordinates> findAllValidMoves(Creature creature, Card card) {
        Set<CoordinatesShift> allPossibleMoves = MovementUtils.getAllPossibleMoves(creature.getSpeed());
        Queue<List<Coordinates>> queue = new ArrayDeque<>();
        List<Coordinates> initialPath = new ArrayList<>();
        Set<Coordinates> checkedCoordiantes = new HashSet<>();


        initialPath.add(creature.getCoordinates());
        queue.add(initialPath);
        checkedCoordiantes.add(creature.getCoordinates());

        while (!queue.isEmpty()) {
            List<Coordinates> path = queue.poll();
            Coordinates current = path.getLast();

            if(creature.isFood(card.getEntity(current))){
                path.removeFirst();
                return path;
            }

            for (CoordinatesShift moveTo : allPossibleMoves) {
                Coordinates next = CardUtils.shift(current, moveTo);


                if((CardUtils.canShiftToSquare(current, next,card) || creature.isFood(card.getEntity(next))) && !checkedCoordiantes.contains(next) ){
                    List<Coordinates> newPath = new ArrayList<>(path);
                    newPath.add(next);
                    queue.offer(newPath);
                    checkedCoordiantes.add(next);
                }
            }
        }
        return Collections.emptyList();
    }
}
