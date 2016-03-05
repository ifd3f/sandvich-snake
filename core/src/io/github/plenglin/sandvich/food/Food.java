package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.plenglin.sandvich.Constants;
import io.github.plenglin.sandvich.IntVector;

public abstract class Food {

    protected IntVector position;

    public Food(IntVector position) {
        this.position = position;
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

    public abstract Texture getTexture();

    public abstract int getPointValue();

    public abstract int getHealth();

    public abstract int getMoney();

}
