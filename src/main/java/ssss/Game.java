package main.java.ssss;

import main.java.ssss.Action.Actions;
import main.java.ssss.Card.Card;
import main.java.ssss.Card.Renderer.CardConsoleRenderer;

import java.util.Map;

public class Game {
    private final Card card;
    private final CardConsoleRenderer renderer;
    private static final int PAUSE_TIME_MS = 1000;

    public Game(Card card, CardConsoleRenderer renderer) {
        this.card = card;
        this.renderer = renderer;
    }

    public  void loop(Map<String, Integer> input, int cardHeight, int cardWidth){
        GameState gameState = new GameState();
        Actions actions = new Actions(card,input);
        actions.setUpRandomPositionsForAllEntities();

        while(true){
            gameState.checkUserInput();
            if(gameState.isFinishRequested()){
                renderer.render(card,cardHeight, cardWidth);
                System.out.println("Simulation finished.");
                break;
            } else if (!gameState.isPaused()) {
                renderer.render(card,cardHeight, cardWidth);
                actions.allActionsInCard(card);
                System.out.println("-------------------------------------------------------------------------");
            }else{
                gameState.waitForResume();
            }

            try {
                Thread.sleep(PAUSE_TIME_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
