package main.java.ssss.Action;

import main.java.ssss.Coordinates.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public class MovementUtils {
    public static Set<CoordinatesShift> getAllPossibleMoves(int speed){
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
}
