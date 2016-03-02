package io.github.plenglin.sandvich;

/**
 * Random helper functions
 */
public class Util {

    public static int randint(int min, int max) {
        return (int) ((max-min)*Math.random() + min);
    }

}
