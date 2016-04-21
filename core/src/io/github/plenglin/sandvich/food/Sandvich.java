package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Main;
import io.github.plenglin.sandvich.assets.Assets;
import io.github.plenglin.util.IntVector;

/**
 * The OG - Original Grub
 */
public class Sandvich extends Food {

    public Sandvich(IntVector position) {
        super(position);
    }

    @Override
    public Texture getTexture() {
        return Main.assets.get(Assets.sandvich);
    }

    @Override
    public int getPointValue() {
        return 100;
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
