package main.java.ssss.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputInformationForCard {
    private static final Scanner scanner = new Scanner(System.in);
    private static final double ROCK_CELLS_PERCENTAGE = 0.10;
    private static final double TREE_CELLS_PERCENTAGE = 0.15;
    private static final double MAX_GRASS_CELLS_PERCENTAGE = 0.5;


    public static Map<String, Integer> input() {
        Map<String, Integer> result = new HashMap<>();
        System.out.println("Hello this is simulation of live");
        int min = 10;
        int max = 80;
        System.out.printf("Please enter width of map(MIN %d and MAX %d)%n", min, max);
        int width = checkForValidInput(min, max);
        result.put("Width", width);

        max = 25;
        System.out.printf("Please enter height of map(MIN %d MAX %d)%n", min, max);
        int height = checkForValidInput(min, max);
        result.put("Height", height);

        int rockCells = (int) (width * height * ROCK_CELLS_PERCENTAGE);
        result.put("Rock", rockCells);

        int treeCells = (int) (width * height * TREE_CELLS_PERCENTAGE);
        result.put("Tree", treeCells);

        min = 0;
        max = (int) (width * height * MAX_GRASS_CELLS_PERCENTAGE);
        System.out.printf("Please enter quantity of grass(MIN %d MAX %d)%n", min, max);
        int grassCells = checkForValidInput(min, max);
        result.put("Grass", grassCells);

        int remainingCells  = (width * height) - (rockCells + treeCells + grassCells);
        System.out.printf("Please enter quantity of Herbivore(MIN %d MAX %d)%n", min, remainingCells );
        int herbivoreCells = checkForValidInput(min, remainingCells );
        result.put("Herbivore", herbivoreCells);

        remainingCells -= herbivoreCells;
        if(remainingCells==0) {
            System.out.println("Remaining cells for predator is 0 so without him.");
            result.put("Predator", remainingCells);
        }
        else{
            System.out.printf("Please enter quantity of Predator(MIN %d MAX %d)%n", min, remainingCells );
            int predatorCells = checkForValidInput(min, remainingCells );
            result.put("Predator", predatorCells);
        }

        return result;

    }

    private static int checkForValidInput(int min, int max) {
        String value;
        int result;

        while (true) {
            value = scanner.nextLine();
            if (value.matches("[0-9]+")) {
                result = Integer.parseInt(value);
                if (result >= min && result <= max)
                    return result;
            }
            System.out.printf("Invalid input. Please enter a valid integer in limit(%d-%d).%n", min, max);
        }
    }
}

