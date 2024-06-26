package main.helper;

import java.util.Random;

public class Randomize {
    public static int getRandomNumber(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound must be less than or equal to upper bound");
        }
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}
