package main.java.ssss;

import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates.Coordinates;

public class EntityBetweenChecker {
    public static boolean isHaveParallelOrMeridianEntitiesBetween(Coordinates source, Coordinates target, Card card) {
        if(source.meridian == target.meridian){
            int parallelShift = source.parrallel < target.parrallel ? 1 : -1;
            for (int parallel = source.parrallel + parallelShift; parallel != target.parrallel; parallel += parallelShift) {
                Coordinates coordinatesBetween = new Coordinates(source.meridian, parallel);
                if (!card.isSquareEmpty(coordinatesBetween))
                    return true;
            }
        } else if (source.parrallel == target.parrallel) {
            int meridianShift = source.meridian < target.meridian ? 1 : -1;
            for (int meridian = source.meridian + meridianShift; meridian != target.meridian; meridian += meridianShift) {
                Coordinates coordinatesBetween = new Coordinates(meridian,source.parrallel);
                if (!card.isSquareEmpty(coordinatesBetween))
                    return true;
            }
        }
        return false;
    }

    public static boolean isHaveDiagonalEntitiesBetween(Coordinates source, Coordinates target, Card card) {
        //допускаем что они на одной диагонали
        int parallelShift = source.parrallel < target.parrallel ? 1 : -1;
        int meridianShift = source.meridian < target.meridian ? 1 : -1;

        for (
                int parallelIndex = source.parrallel + parallelShift,
                meridianIndex = source.meridian + meridianShift;

                parallelIndex != target.parrallel && meridianIndex != target.meridian;

                parallelIndex += parallelShift, meridianIndex += meridianShift
        ) {
            Coordinates coordinatesBetween = new Coordinates(meridianIndex, parallelIndex);
            if (!card.isSquareEmpty(coordinatesBetween)) {
                return true;
            }

        }

        return false;
    }

}
