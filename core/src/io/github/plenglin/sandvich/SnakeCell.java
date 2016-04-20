package io.github.plenglin.sandvich;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.plenglin.sandvich.screen.GameScreen;
import io.github.plenglin.util.IntVector;

import java.util.Iterator;

/**
 * A single cell that a snake takes up. It has followers. Woo, recursion!
 */
public class SnakeCell implements Iterable<SnakeCell> {

    boolean mouthOpen = false; // nice display thing
    private IntVector position;
    private SnakeDirection direction;
    private SnakeCell follower, following; // follower = behind, following = front

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
        position = position.add(direction.vec).add(GameScreen.getDimVector()).mod(GameScreen.getDimVector());
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
        batch.flush();
        if (!isTail()) {
            follower.draw(batch);
        }
        t.dispose();
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

    Pixmap neckPixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);

    public Texture getTexture() {
        if (isHead()) {
            return new Texture(Main.assets.get(mouthOpen ? direction.open : direction.closed).getTextureData());
        }
        if (isTail()) {
            return new Texture(Main.assets.get(following.direction.tail).getTextureData());
        }
        neckPixmap.setColor(Color.CLEAR);
        neckPixmap.fill();
        neckPixmap.setColor(Constants.HEAVY_SKIN);
        for (SnakeDirection v : new SnakeDirection[]{following.direction.getOpposite(), direction}) {
            switch (v) {
                case DOWN:
                    neckPixmap.fillRectangle(Constants.NECK_OFFSET, 0, Constants.HEAVY_NECK, Constants.NECK_LONG_LENGTH);
                    break;
                case UP:
                    neckPixmap.fillRectangle(Constants.NECK_OFFSET, Constants.NECK_OFFSET, Constants.HEAVY_NECK, Constants.NECK_LONG_LENGTH);
                    break;
                case RIGHT:
                    neckPixmap.fillRectangle(0, Constants.NECK_OFFSET, Constants.NECK_LONG_LENGTH, Constants.HEAVY_NECK);
                    break;
                case LEFT:
                    neckPixmap.fillRectangle(Constants.NECK_OFFSET, Constants.NECK_OFFSET, Constants.NECK_LONG_LENGTH, Constants.HEAVY_NECK);
                    break;
            }
        }
        return new Texture(neckPixmap);
    }

}
