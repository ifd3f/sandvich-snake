package io.github.plenglin.sandvich;

import com.badlogic.gdx.graphics.Color;

public class Constants {

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;

    public static final int GRID_DISPLAY_WIDTH = 800;
    public static final int GRID_DISPLAY_HEIGHT = 600;
    public static final int GRID_OFFSET_X = (WINDOW_WIDTH - GRID_DISPLAY_WIDTH) / 2;
    public static final int GRID_OFFSET_Y = (WINDOW_HEIGHT - GRID_DISPLAY_HEIGHT) / 2;
    public static final int GRID_WIDTH = 32;
    public static final int GRID_HEIGHT = 24;
    public static final int CELL_WIDTH = GRID_DISPLAY_WIDTH / GRID_WIDTH;
    public static final int CELL_HEIGHT = GRID_DISPLAY_HEIGHT / GRID_HEIGHT;

    public static final float UPDATE_FAST = 0.05f;
    public static final float UPDATE_SLOW = 0.2f;
    public static final float FOOD_DECAY_TIME = 10;
    public static final float UBER_TIME = 8; // On uber: Medic ubers you for n seconds.
    public static final float INVINCIBILITY_TIME = 3; // On self-hurt: Bite yourself, idiot.
    public static final int MEDIC_COST = 1000;
    public static final int EXISTING_FOOD = 5;
    public static final int FOOD_GROW_LENGTH = 2;
    public static final Color HEAVY_SKIN = new Color(248 / 255f, 153 / 255f, 104 / 255f, 1f);
    public static final int HEAVY_NECK = 24;
    public static final int NECK_OFFSET = 32 - HEAVY_NECK / 2;
    public static final int NECK_LONG_LENGTH = HEAVY_NECK + NECK_OFFSET;
    public static final int HEAVY_HEALTH = 300;
    public static final int HEAVY_OVERHEAL = 350;
    private static final float UPDATE_NORM = 0.1f;
    public static final float UPDATE_SPEED = UPDATE_NORM;
}
