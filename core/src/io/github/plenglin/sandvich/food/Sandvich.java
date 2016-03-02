package io.github.plenglin.sandvich.food;

import com.badlogic.gdx.graphics.Texture;
import io.github.plenglin.sandvich.Assets;
import io.github.plenglin.sandvich.IntVector;
import io.github.plenglin.sandvich.Main;

/**
 *
 */
public class Sandvich extends Food {
    public Sandvich(IntVector position) {
        super(position);
    }

    @Override
    public Texture getTexture() {
        return Main.assets.get(Assets.sandvich);
    }


}
