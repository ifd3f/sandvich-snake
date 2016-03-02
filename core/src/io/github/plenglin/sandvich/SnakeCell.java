package io.github.plenglin.sandvich;

import com.badlogic.gdx.graphics.Texture;

import java.util.Iterator;

/**
 * A single cell that a snake takes up. It has followers. Woo, recursion!
 */
public class SnakeCell implements Iterable<SnakeCell>, GridObject {

    private IntVector position;
    private SnakeCell follower;

    public SnakeCell(IntVector position, SnakeCell follower) {
        this.position = position;
        this.follower = follower;
    }

    /**
     * Move the snake.
     * @param movement How to move it
     */
    public void move(IntVector movement) {
        if (follower != null) {
            follower.move(position.sub(movement));
        }
        position = position.add(movement);
    }

    public boolean isEatingSelf() {
        for (SnakeCell follower: this) {
            if (this.position.equals(follower.position)) {
                return true;
            }
        }
        return false;
    }

    public IntVector getPosition() {
        return position;
    }

    public boolean isTail() {
        return follower == null;
    }

    public SnakeCell getTail() {
        return isTail() ? this : follower.getTail();
    }

    public SnakeCell getFollower() {
        return follower;
    }

    /**
     * @return an iterator of all SnakeCell instances trailing it
     */
    @Override
    public Iterator<SnakeCell> iterator() {
        return new Iterator<SnakeCell>() {
            SnakeCell current = SnakeCell.this;

            @Override
            public boolean hasNext() {
                return current.isTail();
            }

            @Override
            public SnakeCell next() {
                current = current.follower;
                return current;
            }
        };
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
