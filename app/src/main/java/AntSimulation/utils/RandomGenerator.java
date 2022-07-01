package AntSimulation.utils;

import java.util.Random;

public class RandomGenerator {
    private static final Random generator = new Random();

    public static double getRandomDirection() {
        return generator.nextDouble(360);
    }

    public static double getRandom(double min, double max) {
        return generator.nextDouble(min, max);
    }
}
