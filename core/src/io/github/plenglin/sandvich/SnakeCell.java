package io.github.plenglin.sandvich;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;

/**
 * A single cell that a snake takes up. It has followers. Woo, recursion!
 */
public class SnakeCell implements Iterable<SnakeCell> {

    private IntVector position, lastMovement;
    private SnakeCell follower;

    public SnakeCell(IntVector position, SnakeCell follower) {
        this.position = position;
        this.lastMovement = new IntVector(0, 0);
        this.follower = follower;
    }

    /**
     * Move the snake.
     *
     * @param movement How to move it
     * @param doAdd    Add a cell to the end?
     */
    public void move(IntVector movement, boolean doAdd) {
        if (!isTail()) {
            follower.move(lastMovement, doAdd);
        } else if (doAdd) {
            follower = new SnakeCell(position, null);
        }
        position = position.add(movement);
        lastMovement = movement;
    }

    public boolean isEatingSelf() {
        if (isTail()) {
            return false;
        }
        for (SnakeCell follower : this) {
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

    public void draw(SpriteBatch batch, int cellWidth, int cellHeight) {
        Texture t = getTexture();
        batch.draw(t, cellWidth * position.x, cellHeight * position.y, cellWidth, cellHeight, 0, 0, t.getWidth(), t.getHeight(), false, true);
        if (!isTail()) {
            follower.draw(batch, cellWidth, cellHeight);
        }
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
                return current != null && !current.isTail();
            }

            @Override
            public SnakeCell next() {
                current = current.follower;
                return current;
            }
        };
    }

    public boolean occupiesPosition(IntVector query) {
        return position.equals(query) || (!isTail() && follower.occupiesPosition(query));
    }

    public Texture getTexture() {
        return Main.assets.get(Assets.heavy_fwd_open);
    }

}
