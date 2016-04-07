package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Assets;
import io.github.plenglin.sandvich.util.IntVector;
import io.github.plenglin.sandvich.Main;

public class Sticky extends Food {

    public Sticky(IntVector position) {
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
