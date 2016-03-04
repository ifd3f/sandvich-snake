package io.github.plenglin.sandvich;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 *
 */
public class Assets {

    public static final AssetDescriptor<Texture> cake = new AssetDescriptor<Texture>("assets/textures/cake.png", Texture.class);
    public static final AssetDescriptor<Texture> cash = new AssetDescriptor<Texture>("assets/textures/cash.png", Texture.class);
    public static final AssetDescriptor<Texture> chocolate = new AssetDescriptor<Texture>("assets/textures/chocolate.png", Texture.class);
    public static final AssetDescriptor<Texture> medkit = new AssetDescriptor<Texture>("assets/textures/medkit.png", Texture.class);
    public static final AssetDescriptor<Texture> sandvich = new AssetDescriptor<Texture>("assets/textures/sandvich.png", Texture.class);
    public static final AssetDescriptor<Texture> steak = new AssetDescriptor<Texture>("assets/textures/steak.png", Texture.class);
    public static final AssetDescriptor<Texture> sticky = new AssetDescriptor<Texture>("assets/textures/sticky.png", Texture.class);

    public static final AssetDescriptor<Texture> heavy_left_open = new AssetDescriptor<Texture>("assets/textures/heavy_left_open.png", Texture.class);
    public static final AssetDescriptor<Texture> heavy_left_closed = new AssetDescriptor<Texture>("assets/textures/heavy_left_closed.png", Texture.class);
    public static final AssetDescriptor<Texture> heavy_right_open = new AssetDescriptor<Texture>("assets/textures/heavy_right_open.png", Texture.class);
    public static final AssetDescriptor<Texture> heavy_right_closed = new AssetDescriptor<Texture>("assets/textures/heavy_right_closed.png", Texture.class);
    public static final AssetDescriptor<Texture> heavy_fwd_open = new AssetDescriptor<Texture>("assets/textures/heavy_fwd_open.png", Texture.class);
    public static final AssetDescriptor<Texture> heavy_fwd_closed = new AssetDescriptor<Texture>("assets/textures/heavy_fwd_closed.png", Texture.class);
    public static final AssetDescriptor<Texture> heavy_back = new AssetDescriptor<Texture>("assets/textures/heavy_back.png", Texture.class);

    public static final AssetDescriptor<Texture> medic = new AssetDescriptor<Texture>("assets/textures/medic.png", Texture.class);

    public static final AssetDescriptor<FreeTypeFontGenerator> title_font = new AssetDescriptor<FreeTypeFontGenerator>("assets/fonts/TF2Build.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> header_font = new AssetDescriptor<FreeTypeFontGenerator>("assets/fonts/TF2.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> normal_font = new AssetDescriptor<FreeTypeFontGenerator>("assets/fonts/TF2secondary.ttf", FreeTypeFontGenerator.class);

}
