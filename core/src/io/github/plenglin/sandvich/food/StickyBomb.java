package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Main;
import io.github.plenglin.sandvich.assets.Assets;
import io.github.plenglin.util.IntVector;

/**
 * It's like steak, but more high steaks.
 */
public class StickyBomb extends Food {

    public StickyBomb(IntVector position) {
        super(position);
    }

    @Override
    public Texture getTexture() {
        return Main.assets.get(Assets.sticky);
    }

    @Override
    public int getPointValue() {
        return 200;
    }

    @Override
    public int getHealth() {
        return -100;
    }

    @Override
    public int getMoney() {
        return 0;
    }

}
