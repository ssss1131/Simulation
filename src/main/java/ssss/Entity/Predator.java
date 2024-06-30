package main.java.ssss.Entity;

import main.java.ssss.BFS;
import main.java.ssss.Card.Card;
import main.java.ssss.Coordinates;

import java.util.List;

public class Predator extends Creature {
    private final int strength;

    public Predator(Coordinates coordinates, int speed, int healthPoints, int strength) {
        super(coordinates, speed, healthPoints);
        this.strength = strength;
    }

    /**
     * Описывает метод для передвижение хищников по заданным координатам
     * <p>
     *     Данный метод реализует передвижение хишников, с помощью BFS находим лист координат для передвижение.
     *     Первая проверка является ли пустым список проверяет может ли он в целом совершить ход.
     *     Вторая проверка пустого листа проверяет дошли  ли мы до травоядного, если он пуст значит мы дошли
     *     и надо нанести урон либо убить.Если он не пуст то мы меняем позицию и понижаем здоровье
     *
     */

    @Override
    public void makeMove(Card card) {
        List<Coordinates> allMoves = BFS.findAllValidMoves(this, card);
        if (!allMoves.isEmpty()) {
            Coordinates coordinatesToMove = allMoves.removeFirst();
            if (allMoves.isEmpty()) {
                Creature attackedHerbivore = (Creature) card.getEntity(coordinatesToMove);
                if (attackedHerbivore.getHealthPoints() - this.strength <= 0) {
                    this.healthPointsUp(80);
                    card.changePosition(this.coordinates, coordinatesToMove);
                } else {
                    attackedHerbivore.attacked(this.strength);
                }
            } else {
                card.changePosition(this.coordinates, coordinatesToMove);
                this.healthPointsDown();
            }
        }


    }
}
