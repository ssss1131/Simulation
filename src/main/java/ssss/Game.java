package main.java.ssss;

import main.java.ssss.Card.Card;
import main.java.ssss.Card.CardConsoleRenderer;
import main.java.ssss.Card.InputInformationForCard;

import java.util.Map;

public class Game {
    public  void gameLoop(){

        CardConsoleRenderer renderer = new CardConsoleRenderer();
        GameState gameState = new GameState();
        Map<String, Integer> input = InputInformationForCard.input();

        int cardWidth = input.get("Width");
        int cardHeight = input.get("Height");
        Card card = new Card(cardWidth, cardHeight);

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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
