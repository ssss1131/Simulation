package main.java.ssss.Card.Renderer;

import main.java.ssss.Card.Card;
import main.java.ssss.Card.CardUtils;
import main.java.ssss.Entity.Herbivore;
import main.java.ssss.Entity.Predator;

public class PrintAboutCard {
    public static void allInformation(Card card){
        int countOfHerbivore = CardUtils.getAllAliveCreaturesOfType(Herbivore.class, card).size();
        int countOfPredator = CardUtils.getAllAliveCreaturesOfType(Predator.class,card).size();
        System.out.println("Herbivore left: " + countOfHerbivore);
        System.out.println("Predator left: " + countOfPredator);
        System.out.println("[S]top to stop the simulation");
        System.out.println("[F]inish to finish the simulation\n");
    }
}
