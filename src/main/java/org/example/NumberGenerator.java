package org.example;

import java.util.Random;

public class NumberGenerator {

    private static final Random rand = new Random();

    public static int getRandomNumber(int range) {
        return rand.nextInt(range);
    }

}
