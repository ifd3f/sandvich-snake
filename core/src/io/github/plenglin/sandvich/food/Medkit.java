package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.assets.Assets;
import io.github.plenglin.util.IntVector;
import io.github.plenglin.sandvich.Main;

/**
 * You don't even need a medical license to use this!
 */
public class Medkit extends Food {

    public Medkit(IntVector position) {
        super(position);
    }

    @Override
    public Texture getTexture() {
        return Main.assets.get(Assets.medkit);
    }

    @Override
    public int getPointValue() {
        return 100;
    }

    @Override
    public int getHealth() {
        return 100;
    }

    @Override
    public int getMoney() {
        return 0;
    }

}
