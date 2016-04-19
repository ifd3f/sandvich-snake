package io.github.plenglin.sandvich;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.plenglin.sandvich.assets.Assets;
import io.github.plenglin.sandvich.assets.Font;
import io.github.plenglin.sandvich.screen.GameScreen;

public class Main extends Game {

    public static AssetManager assets;
    public static SpriteBatch batch;
    public static ShapeRenderer shape;
    public static OrthographicCamera camera;

    public static void loadAssets() {

        assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));

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

        assets.load(Assets.heavy_tail_up);
        assets.load(Assets.heavy_tail_down);
        assets.load(Assets.heavy_tail_left);
        assets.load(Assets.heavy_tail_right);

        assets.load(Assets.heavy_portrait);

        assets.load(Assets.title_font);
        assets.load(Assets.header_font);
        assets.load(Assets.normal_font);

        assets.finishLoading();
    }

    @Override
    public void create() {
        assets = new AssetManager();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        loadAssets();
        Font.generateFonts();
        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        assets.dispose();
    }

}
