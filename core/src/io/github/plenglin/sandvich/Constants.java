package io.github.plenglin.sandvich;

public class Constants {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 30;
    public static final int CELL_WIDTH = WINDOW_WIDTH / GRID_WIDTH;
    public static final int CELL_HEIGHT = WINDOW_HEIGHT / GRID_HEIGHT;

    public static final float UPDATE_FAST = 0.05f;
    public static final float UPDATE_NORM = 0.1f;
    public static final float UPDATE_SLOW = 0.2f;
    public static final float UPDATE_SPEED = UPDATE_NORM;

    public static final int EXISTING_FOOD = 5;
    public static final int FOOD_GROW_LENGTH = 10;

}
