package main.java.ssss.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputInformationForCard {
    private static final Scanner scanner = new Scanner(System.in);

    public static Map<String, Integer> input() {
        Map<String, Integer> result = new HashMap<>();
        System.out.println("Hello this is simulation of live");
        int min = 10;
        int max = 80;
        System.out.printf("Please enter width of map(MIN %d and MAX %d)%n", min, max);
        int width = checkForValidInput(min, max);
        result.put("Width", width);

        max = 25; // for height
        System.out.printf("Please enter height of map(MIN %d MAX %d)%n", min, max);
        int height = checkForValidInput(min, max);
        result.put("Height", height);

        min = 0;
        max = (int) (width * height * 0.10);
        System.out.printf("Please enter quantity of rock(MIN %d MAX %d)%n", min, max);
        int rockCells = checkForValidInput(min, max);
        result.put("Rock", rockCells);

        max = (int) (width * height * 0.15);
        System.out.printf("Please enter quantity of tree(MIN %d MAX %d)%n", min, max);
        int treeCells = checkForValidInput(min, max);
        result.put("Tree", treeCells);

        max = (int) (width * height * 0.5);
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

