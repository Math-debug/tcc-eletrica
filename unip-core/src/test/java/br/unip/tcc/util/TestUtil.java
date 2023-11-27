package br.unip.tcc.util;

import java.util.Random;

public class TestUtil {
    public static long generateRandomLong() {
        Random random = new Random();
        return (long) (random.nextDouble() * (100 - 0 + 1));
    }
}
