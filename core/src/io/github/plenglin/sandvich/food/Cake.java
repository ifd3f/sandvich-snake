package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Main;
import io.github.plenglin.sandvich.assets.Assets;
import io.github.plenglin.util.IntVector;

/**
 * Heavies are like onions and cake, they have layers.
 */
public class Cake extends Food {

    public Cake(IntVector position) {
        super(position);
    }

    @Override
    public Texture getTexture() {
        return Main.assets.get(Assets.cake);
    }

    @Override
    public int getPointValue() {
        return 150;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getMoney() {
        return 0;
    }

}
