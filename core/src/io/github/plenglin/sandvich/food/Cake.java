package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Assets;
import io.github.plenglin.sandvich.util.IntVector;
import io.github.plenglin.sandvich.Main;

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
