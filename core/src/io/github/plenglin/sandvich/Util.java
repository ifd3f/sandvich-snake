package io.github.plenglin.sandvich;

/**
 * Random helper functions
 */
public class Util {

    public static int randint(int min, int max) {
        return (int) ((max - min) * Math.random() + min);
    }

    /**
     * Output a square wave.
     * @param time the time
     * @param period the period
     * @return the value
     */
    public static boolean squareWave(long time, long period) {
        return (time % (period / 2)) <= period / 4;
    }

}
