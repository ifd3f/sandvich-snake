package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.plenglin.sandvich.IntVector;

public abstract class Food {

    protected IntVector position;

    public Food(IntVector position) {
        this.position = position;
    }

    public IntVector getPosition() {
        return position;
    }

    public void draw(SpriteBatch batch, int cellWidth, int cellHeight) {
        Texture t = getTexture();
        batch.draw(t, cellWidth*position.x, cellHeight*position.y, cellWidth, cellHeight, 0, 0, t.getWidth(), t.getHeight(), false, true);
    }

    public abstract Texture getTexture();

}
