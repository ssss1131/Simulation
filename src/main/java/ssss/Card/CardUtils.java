package main.java.ssss.Card;

import main.java.ssss.Coordinates.Coordinates;
import main.java.ssss.Coordinates.CoordinatesShift;
import main.java.ssss.Entity.Creature;
import main.java.ssss.Entity.Entity;
import main.java.ssss.Entity.Grass;
import main.java.ssss.EntityBetweenChecker;

import java.util.ArrayList;
import java.util.List;

public class CardUtils {
    public static Coordinates shift(Coordinates coordinates, CoordinatesShift coordinatesShift) {
        return new Coordinates(coordinates.meridian + coordinatesShift.getMeridianShift(), coordinates.parrallel + coordinatesShift.getParallelShift());
    }
    public static boolean canShiftToSquare(Coordinates source, Coordinates target,Card card) {
        //допускается что данный метод будет использоватся только при соблюдений условий трех методов в EntityBetweenChecker
        boolean isMeridianValid = target.meridian > 0 && target.meridian <= card.getCardHeight();
        boolean isParallelValid = target.parrallel > 0 && target.parrallel <= card.getCardWidth();

        boolean validSquare = (card.isSquareEmpty(target)) && isMeridianValid && isParallelValid;
        if(validSquare){
            boolean checkParallelEqual = source.parrallel == target.parrallel;
            boolean checkMeridianEqual = source.meridian == target.meridian;
            boolean isHaveEntityBetween;
            if(checkParallelEqual || checkMeridianEqual) isHaveEntityBetween = EntityBetweenChecker.isHaveParallelOrMeridianEntitiesBetween(source,target,card);
            else{
                isHaveEntityBetween = EntityBetweenChecker.isHaveDiagonalEntitiesBetween(source,target,card);
            }
            return !isHaveEntityBetween;

        }

        return false;
    }

    public static int getGrassQuantity(Card card){
        int result = 0;
        for(Entity entity:card.getEntities().values()){
            if(entity instanceof Grass){
                result++;
            }
        }
        return result;
    }

    public static List<Creature> getAllAliveCreaturesOfType(Class<? extends Creature> entityType, Card card) {
        List<Creature> result = new ArrayList<>();
        for (Entity entity : card.getEntities().values()) {
            if (entityType.isInstance(entity)) {
                Creature creature =(Creature) entity;
                result.add(creature);
            }

        }
        return result;
    }


}
