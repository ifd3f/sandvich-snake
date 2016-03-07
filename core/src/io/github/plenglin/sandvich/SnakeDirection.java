package io.github.plenglin.sandvich;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

/**
 * The direction the snake can move in
 */
public enum SnakeDirection {

    STOP(0, 0, Assets.heavy_fwd_closed, Assets.heavy_fwd_closed, Assets.heavy_tail_up),
    UP(0, -1, Assets.heavy_back, Assets.heavy_back, Assets.heavy_tail_up),
    DOWN(0, 1, Assets.heavy_fwd_open, Assets.heavy_fwd_closed, Assets.heavy_tail_down),
    LEFT(-1, 0, Assets.heavy_left_open, Assets.heavy_left_closed, Assets.heavy_tail_left),
    RIGHT(1, 0, Assets.heavy_right_open, Assets.heavy_right_closed, Assets.heavy_tail_right);

    public final IntVector vec;
    public final AssetDescriptor<Texture> open, closed, tail;

    SnakeDirection(int x, int y, AssetDescriptor<Texture> open, AssetDescriptor<Texture> closed, AssetDescriptor<Texture> tail) {
        this.vec = new IntVector(x, y);
        this.open = open;
        this.closed = closed;
        this.tail = tail;
    }

    public static SnakeDirection toDirection(IntVector direction) {
        for (SnakeDirection d : new SnakeDirection[]{STOP, UP, DOWN, LEFT, RIGHT}) {
            if (direction.equals(d.vec)) {
                return d;
            }
        }
        return null;
    }

    public SnakeDirection getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return STOP;
    }

}
