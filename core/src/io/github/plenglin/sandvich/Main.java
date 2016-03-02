package io.github.plenglin.sandvich;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {

    public static AssetManager assets;

	@Override
	public void create () {
        assets = new AssetManager();
        loadAssets();
        setScreen(new GameScreen());
	}

	@Override
	public void render () {
        super.render();
	}

    public void loadAssets() {
        assets.load(Assets.cake);
        assets.load(Assets.cash);
        assets.load(Assets.chocolate);
        assets.load(Assets.heavy_back);
        assets.load(Assets.medkit);
        assets.load(Assets.sandvich);
        assets.load(Assets.steak);
        assets.load(Assets.sticky);

        assets.load(Assets.medic);

        assets.load(Assets.heavy_back);
        assets.load(Assets.heavy_fwd_open);
        assets.load(Assets.heavy_fwd_closed);
        assets.load(Assets.heavy_left_open);
        assets.load(Assets.heavy_left_closed);
        assets.load(Assets.heavy_right_open);
        assets.load(Assets.heavy_right_closed);

        assets.finishLoading();
    }

    @Override
    public void dispose() {
        assets.dispose();
    }

}
