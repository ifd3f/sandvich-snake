package io.github.plenglin.sandvich.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import io.github.plenglin.sandvich.Main;

/**
 *
 */
public class Font {

    public static BitmapFont stats;

    public static void generateFonts() {
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 20;
        params.color = new Color(Color.WHITE);
        params.flip = true;
        stats = Main.assets.get(Assets.normal_font).generateFont(params);
    }

}
