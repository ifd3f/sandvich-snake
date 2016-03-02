package io.github.plenglin.sandvich;

/**
 * The direction the snake can move in
 */
public enum SnakeDirection {

    STOP (0, 0),
    UP (0, -1),
    DOWN (0, 1),
    LEFT (-1, 0),
    RIGHT (1, 0);

    public final IntVector vec;

    SnakeDirection(int x, int y) {
        vec = new IntVector(x, y);
    }
}
