package main.java.ssss.Card;

import main.java.ssss.Coordinates;
import main.java.ssss.CoordinatesShift;
import main.java.ssss.Entity.Creature;
import main.java.ssss.Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {
    public Map<Coordinates, Entity> entities = new HashMap<>();
    private final int cardWidth;
    private final int cardHeight;

    public Card(int cardWidth, int cardHeight) {
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }


    public boolean isSquareEmpty(Coordinates coordinates) {
        return entities.get(coordinates) == null;
    }

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void changePosition(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntities(to, entity);
    }


    public List<Creature> getAllAliveCreaturesOfType(Class<? extends Creature> entityType) {
        List<Creature> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entityType.isInstance(entity)) {
                Creature creature =(Creature) entity;
                result.add(creature);
            }
        }
        return result;
    }


    public boolean canShiftToSquare(Coordinates source, Coordinates target) {
        //допускается что данный метод будет использоватся только при соблюдений условий трех методов в CardUtils
        boolean isMeridianValid = target.meridian > 0 && target.meridian <= cardHeight;
        boolean isParallelValid = target.parrallel > 0 && target.parrallel <= cardWidth;

        boolean validSquare = (isSquareEmpty(target)) && isMeridianValid && isParallelValid;
        if(validSquare){
            boolean checkParallelEqual = source.parrallel == target.parrallel;
            boolean checkMeridianEqual = source.meridian == target.meridian;
            boolean isHaveEntityBetween;
            if(checkParallelEqual) isHaveEntityBetween = CardUtils.isHaveMeridianEntitiesBetween(source,target,this);
            else if(checkMeridianEqual) isHaveEntityBetween = CardUtils.isHaveParallelEntitiesBetween(source,target, this);
            else{
                isHaveEntityBetween = CardUtils.isHaveDiagonalEntitiesBetween(source,target,this);
            }
            return !isHaveEntityBetween;

        }

        return false;
    }

    public Coordinates shift(Coordinates coordinates, CoordinatesShift coordinatesShift) {
        return new Coordinates(coordinates.meridian + coordinatesShift.meridianShift, coordinates.parrallel + coordinatesShift.parallelShift);
    }


}
