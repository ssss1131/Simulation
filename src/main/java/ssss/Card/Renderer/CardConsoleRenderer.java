package main.java.ssss.Card.Renderer;

import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates.Coordinates;
import main.java.ssss.Entity.Creature;
import main.java.ssss.Entity.Entity;


public class CardConsoleRenderer {

    private static final String ANSI_ROCK_BACKGROUND = "\u001B[48;5;240m"; // Серый фон для камня
    private static final String ANSI_TREE_BACKGROUND = "\u001B[48;5;22m";  // Зеленый фон для дерева
    private static final String ANSI_GRASS_BACKGROUND = "\u001B[48;5;28m"; // Зеленый фон для травы
    private static final String ANSI_PREDATOR_BACKGROUND = "\u001B[48;5;52m"; // Красный фон для хищников


    private static final String ANSI_75_BLUE_BACKGROUND = "\u001B[48;5;32m"; //75%-100% HP
    public static final String ANSI_50_YELLOW_BACKGROUND = "\u001B[43m"; // 50%-75% HP
    public static final String ANSI_25_ORANGE_BACKGROUND = "\u001B[48;5;208m"; // 25%-50% HP
    public static final String ANSI_1_RED_BACKGROUND = "\u001B[41m"; // 1%-25% HP

    private static final String ANSI_NEUTRAL_BACKGROUND_2 = "\u001B[48;5;236m"; // Светло-серый фон
    private static final String ANSI_RESET = "\u001B[0m"; //Сброс кода
    private static final String ANSI_PREDATOR_DEATH = " ☠️ ";
    private static final String ANSI_HERBIVORE_DEATH = " 🥩 ";



    public void render(Card card, int cardHeight, int cardWidth) {
        for (int meredian = cardHeight; meredian >= 1; meredian--) {
            String line = "";
            for (int parallel = 1; parallel <= cardWidth; parallel++) {
                Coordinates coordinates = new Coordinates(meredian, parallel);
                Entity entity = card.getEntity(coordinates);
                if (entity != null) {
                    line += getEntitySprite(entity);
                } else {
                    line += getSpriteForEmptySquare();
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
        PrintAboutCard.allInformation(card);
    }

    private String getSpriteForEmptySquare() {
        return ANSI_NEUTRAL_BACKGROUND_2 + "    ";
    }

    private String getEntitySprite(Entity entity) {
        return ANSI_NEUTRAL_BACKGROUND_2 + selectUnicodeSpriteForEntity(entity);
    }

    private String selectUnicodeSpriteForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Rock" -> ANSI_ROCK_BACKGROUND + " ⛰ ";
            case "Tree" -> ANSI_TREE_BACKGROUND + " 🌳 ";
            case "Grass" -> ANSI_GRASS_BACKGROUND + " 🌿 ";
            case "Herbivore" -> chooseColorForHerbivore((Creature) entity);
            case "Predator" -> chooseUnicodeSpriteForPredator((Creature) entity);
            default -> "";
        };
    }

    private String chooseUnicodeSpriteForPredator(Creature creature){
        if(creature.getHealthPoints()<=0){
            return ANSI_PREDATOR_DEATH;
        }else{
            return ANSI_PREDATOR_BACKGROUND + " 🐺 ";
        }


    }

    private String chooseColorForHerbivore(Creature creature){
        int currentHP = creature.getHealthPoints();
        int initialHpPercentage = (currentHP*100)/creature.initialHealthPoints;
        if(initialHpPercentage>75){
            return ANSI_75_BLUE_BACKGROUND + " 🐑 ";
        }else if(initialHpPercentage>50){
            return ANSI_50_YELLOW_BACKGROUND + " 🐑 ";
        }else if(initialHpPercentage>25){
            return ANSI_25_ORANGE_BACKGROUND + " 🐑 ";
        }else if (initialHpPercentage>1){
            return ANSI_1_RED_BACKGROUND + " 🐑 ";
        }
        return ANSI_HERBIVORE_DEATH;
    }

}
