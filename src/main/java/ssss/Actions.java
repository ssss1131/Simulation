package main.java.ssss;

import main.java.ssss.Card.Card;
import main.java.ssss.Entity.*;

import java.util.Map;
import java.util.Random;

public class Actions {
    private final Card card;
    private final Random random = new Random();
    private final Map<String, Integer> cells;

    public Actions(Card card, Map<String, Integer> cells) {
        this.card = card;
        this.cells = cells;
    }

    public void setUpRandomPositionsForAllEntities() {
        int rockCells = cells.get("Rock");
        int treeCells = cells.get("Tree");
        int grassCells = cells.get("Grass");
        int herbivoreCells = cells.get("Herbivore");
        int predatorCells = cells.get("Predator");
        setUpPositionForEntity(Rock.class, rockCells);
        setUpPositionForEntity(Tree.class, treeCells);
        setUpPositionForEntity(Grass.class, grassCells);
        setUpPositionForEntity(Herbivore.class, herbivoreCells);
        setUpPositionForEntity(Predator.class, predatorCells);
    }

    private void setUpPositionForEntity(Class<? extends Entity> entityType, int quantity) {
        while (quantity > 0) {
            int meridian = random.nextInt(1, cells.get("Height") + 1);
            int parallel = random.nextInt(1, cells.get("Width") + 1);
            Coordinates coordinates = new Coordinates(meridian, parallel);
            if (card.isSquareEmpty(coordinates)) {
                Entity entity;
                try {
                    if (entityType == Herbivore.class) {
                        entity = new Herbivore(coordinates, random.nextInt(1, 3), random.nextInt(50, 150));
                    } else if (entityType == Predator.class) {
                        entity = new Predator(coordinates, random.nextInt(1, 3), random.nextInt(50, 150), random.nextInt(10, 50));
                    } else {
                        entity = entityType.getConstructor(Coordinates.class).newInstance(coordinates);
                    }
                    card.setEntities(coordinates, entity);
                    quantity--;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод предназначен для совершение всех действий над Creature
     * <p>
     *     Проходимся по всем живым существам какого то типа, для хищников сперва проверка не умер ли он
     *     если умер то убираем его из карты.Вторая проверка проверяет есть ли на карте травоядные, если нету
     *     то симуляция завершается.
     */

    public void allActionsInCard(Card card) {
        for (Creature creature : card.getAllAliveCreaturesOfType(Predator.class)) {
            if (isPredatorDead(creature)) {
                card.removeEntity(creature.coordinates);

            } else if (card.getAllAliveCreaturesOfType(Herbivore.class).isEmpty()) {
                GameState.finishRequested = true;
                break;

            } else {
                creature.makeMove(card);
            }
        }

        for (Creature creature : card.getAllAliveCreaturesOfType(Herbivore.class)) {
            if(creature.getHealthPoints()<=0){
                card.removeEntity(creature.coordinates);
            }else{
                creature.makeMove(card);
            }


        }

        if (Grass.counter < cells.get("Grass")) {
            regenerateGrass();
        }
    }

    private void regenerateGrass() {
        setUpPositionForEntity(Grass.class, cells.get("Grass") - Grass.counter);
    }

    private boolean isPredatorDead(Creature creature) {
        return creature.getHealthPoints() <= 0;
    }

}
