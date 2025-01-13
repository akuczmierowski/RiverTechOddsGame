package pl.andrzejkuczmierowski.RiverTechOddsGame.utils;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator{
    private final Random random = new Random();
    @Override
    public int generate() {
        return random.nextInt(10) + 1;
    }
}
