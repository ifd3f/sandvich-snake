package io.github.plenglin.sandvich;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;

/**
 * A single cell that a snake takes up. It has followers. Woo, recursion!
 */
public class SnakeCell implements Iterable<SnakeCell> {

    boolean mouthOpen = false; // nice display thing
    private IntVector position;
    private SnakeDirection direction;
    private SnakeCell follower, following;

    public SnakeCell(IntVector position, SnakeCell follower, SnakeCell following, SnakeDirection direction) {
        this.position = position;
        this.direction = direction;
        this.follower = follower;
        this.following = following;
    }

    /**
     * Move the snake.
     *
     * @param direction How to move it
     * @param doAdd     Add a cell to the end?
     */
    public void move(SnakeDirection direction, boolean doAdd) {
        if (!isTail()) {
            follower.move(this.direction, doAdd);
        } else if (doAdd) {
            follower = new SnakeCell(position, null, this, this.direction);
        }
        position = position.add(direction.vec);
        this.direction = direction;
        mouthOpen = !mouthOpen;
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

    public boolean isOutOfBounds() {
        return (0 > position.x || position.x >= Constants.GRID_WIDTH) || (0 > position.y || position.y >= Constants.GRID_HEIGHT);
    }

    public SnakeCell getTail() {
        return isTail() ? this : follower.getTail();
    }

    public SnakeCell getFollower() {
        return follower;
    }

    public boolean isHead() {
        return following == null;
    }

    public SnakeCell getHead() {
        return isHead() ? this : follower.getHead();
    }

    public SnakeCell getFollowing() {
        return following;
    }

    public void draw(SpriteBatch batch) {
        Texture t = getTexture();
        batch.draw(
                t,
                Constants.CELL_WIDTH * position.x + Constants.GRID_OFFSET_X,
                Constants.CELL_HEIGHT * position.y + Constants.GRID_OFFSET_Y,
                Constants.CELL_WIDTH, Constants.CELL_HEIGHT,
                0, 0, t.getWidth(), t.getHeight(),
                false, true
        );
        if (!isTail()) {
            follower.draw(batch);
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
        if (isHead()) {
            return Main.assets.get(mouthOpen ? direction.open : direction.closed);
        }
        if (isTail()) {
            return Main.assets.get(SnakeDirection.toDirection(following.position.sub(position)).tail);
        }
        IntVector a = following.position;
        IntVector b = follower.position;
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Constants.HEAVY_SKIN);
        for (IntVector v : new IntVector[]{a, b}) {
            switch (SnakeDirection.toDirection(position.sub(v))) {
                case DOWN:
                    pixmap.fillRectangle(Constants.NECK_OFFSET, 0, Constants.HEAVY_NECK, Constants.NECK_LONG_LENGTH);
                    break;
                case UP:
                    pixmap.fillRectangle(Constants.NECK_OFFSET, Constants.NECK_OFFSET, Constants.HEAVY_NECK, Constants.NECK_LONG_LENGTH);
                    break;
                case RIGHT:
                    pixmap.fillRectangle(0, Constants.NECK_OFFSET, Constants.NECK_LONG_LENGTH, Constants.HEAVY_NECK);
                    break;
                case LEFT:
                    pixmap.fillRectangle(Constants.NECK_OFFSET, Constants.NECK_OFFSET, Constants.NECK_LONG_LENGTH, Constants.HEAVY_NECK);
                    break;
            }
        }
        Texture t = new Texture(pixmap);
        pixmap.dispose();
        return t;
    }

}
