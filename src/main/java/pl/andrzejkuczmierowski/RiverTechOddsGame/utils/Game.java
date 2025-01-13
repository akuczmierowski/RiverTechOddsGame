package pl.andrzejkuczmierowski.RiverTechOddsGame.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Component
public class Game {

    private final NumberGenerator numberGenerator;

    public Game(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public double bet(int chosenNumber, double amount) {
        int drawnNumber = numberGenerator.generate();
        if (drawnNumber == chosenNumber) {
            return amount * 10;
        } else if (Math.abs(drawnNumber - chosenNumber) == 1) {
            return amount * 5;
        } else if (Math.abs(drawnNumber - chosenNumber) == 2) {
            return amount / 2;
        }
        return -amount;
    }

}
