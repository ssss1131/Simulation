package main.java.ssss.Card;

import main.java.ssss.Coordinates.Coordinates;
import main.java.ssss.Entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private final Map<Coordinates, Entity> entities = new HashMap<>();
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
        entity.setCoordinates(coordinates);
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


    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public int getCardHeight() {
        return cardHeight;
    }

    public int getCardWidth() {
        return cardWidth;
    }
}
