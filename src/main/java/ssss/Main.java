package main.java.ssss;

import main.java.ssss.Card.Card;
import main.java.ssss.Card.InputInformationForCard;
import main.java.ssss.Card.Renderer.CardConsoleRenderer;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> input = InputInformationForCard.input();
        int cardWidth = input.get("Width");
        int cardHeight = input.get("Height");

        Card card = new Card(cardWidth, cardHeight);
        CardConsoleRenderer cardConsoleRenderer = new CardConsoleRenderer();
        Game game = new Game(card, cardConsoleRenderer);

        game.loop(input, cardHeight, cardWidth);
    }
}
