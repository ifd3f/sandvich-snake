package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Assets;
import io.github.plenglin.sandvich.IntVector;
import io.github.plenglin.sandvich.Main;

public class Steak extends Food {

    public Steak(IntVector position) {
        super(position);
    }

    @Override
    public Texture getTexture() {
        return Main.assets.get(Assets.steak);
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
