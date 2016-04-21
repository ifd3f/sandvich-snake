package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.plenglin.sandvich.Constants;
import io.github.plenglin.util.IntVector;

/**
 * A generic food class.
 */
public abstract class Food {

    private IntVector position;
    private float timeLeft;

    Food(IntVector position) {
        this.position = position;
        this.timeLeft = Constants.FOOD_DECAY_TIME;
    }

    public IntVector getPosition() {
        return position;
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
    }

    public void update(float delta) {
        timeLeft -= delta;
    }

    public boolean hasDecayed() {
        return timeLeft <= 0;
    }

    public boolean allowOverheal() {
        return false;
    }

    protected abstract Texture getTexture();

    public abstract int getPointValue();

    public abstract int getHealth();

    public abstract int getMoney();

}
