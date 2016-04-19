package io.github.plenglin.sandvich.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.plenglin.sandvich.*;
import io.github.plenglin.sandvich.assets.Assets;
import io.github.plenglin.sandvich.assets.Font;
import io.github.plenglin.sandvich.food.*;
import io.github.plenglin.util.IntVector;
import io.github.plenglin.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * The screen that the game takes place on
 */
public class GameScreen implements Screen, InputProcessor {

    SnakeDirection direction, nextDirection;
    Texture img;
    SnakeCell snake;
    List<Food> food;
    InvulnType invulnType;
    float timeToNextUpdate, invulnLeft;
    int lengthToGrow, score, money, health;
    boolean gameOver = false;

    enum InvulnType {
        NONE, UBER, DAMAGE
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

        food = new ArrayList<Food>();
        snake = new SnakeCell(getRandomCell(), null, null, SnakeDirection.STOP);

        direction = SnakeDirection.STOP;
        nextDirection = direction;

        timeToNextUpdate = 0;

        invulnLeft = 0;
        invulnType = InvulnType.NONE;
        lengthToGrow = 0;
        health = 300;
        money = 0;
        score = 0;
    }

    public void updateTimers(float delta) {
        timeToNextUpdate -= delta;
        invulnLeft -= delta;
    }

    @Override
    public void render(float delta) {

        /** Update **/
        updateTimers(delta);
        List<Food> foodToRemove = new ArrayList<>();

        if (!isInvulnerable()) {
            invulnType = InvulnType.NONE;
        }

        // Remove decaying food
        for (Food f: food) {
            f.update(delta);
            if (f.hasDecayed()) {
                foodToRemove.add(f);
            }
        }

        // Move the snake on periodic updates
        if (timeToNextUpdate <= 0) {

            // Grow the snake, if necessary
            if (lengthToGrow > 0) {
                snake.move(direction, true);
                lengthToGrow--;
            } else {
                snake.move(direction, false);
            }

            // Check if the snake is eating itself
            if (snake.isEatingSelf() && !isInvulnerable()) {
                health -= 250;
                invulnType = InvulnType.DAMAGE;
                invulnLeft = Constants.INVINCIBILITY_TIME;
            }

            // Check if the snake is eating any food
            for (Food f : food) {
                if (snake.getPosition().equals((f.getPosition()))) {
                    foodToRemove.add(f);
                    lengthToGrow += Constants.FOOD_GROW_LENGTH;
                    score += f.getPointValue();
                    health += f.getHealth();
                    money += f.getMoney();
                    int healthCap;
                    if (f.allowOverheal() || health > Constants.HEAVY_HEALTH) {
                        healthCap = Constants.HEAVY_OVERHEAL;
                    } else {
                        healthCap = Constants.HEAVY_HEALTH;
                    }
                    health = health > healthCap ? healthCap : health;
                    break;
                }
            }

            timeToNextUpdate = Constants.UPDATE_SPEED;
            direction = nextDirection;

        }
        for (Food f: foodToRemove) {
            food.remove(f);
        }
        if (food.size() < Constants.EXISTING_FOOD) {
            food.add(Main.spawner.create(findNewFoodLocation()));
        }
        if (health <= 0) {
            gameOver();
        }

        /** Render **/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Main.batch.setProjectionMatrix(Main.camera.combined);
        Main.shape.setProjectionMatrix(Main.camera.combined);

        // Draw border
        Main.shape.begin(ShapeRenderer.ShapeType.Line);
        Main.shape.setColor(Color.BLUE);
        Main.shape.rect(Constants.GRID_OFFSET_X, Constants.GRID_OFFSET_Y, Constants.GRID_DISPLAY_WIDTH, Constants.GRID_DISPLAY_HEIGHT);
        Main.shape.end();

        Main.batch.begin();
        drawObjects();
        drawStats();
        Main.batch.end();
    }

    public void drawObjects() {
        for (Food f : food) {
            f.draw(Main.batch);
        }

        if (invulnType == InvulnType.UBER) {
            Main.batch.setColor(Color.RED);
        }
        if (!(invulnType == InvulnType.DAMAGE && Util.squareWave(System.currentTimeMillis(), 1000))) {
            snake.draw(Main.batch);
        }
    }

    public void drawStats() {
        Main.batch.setColor(Color.WHITE);

        Main.batch.draw(Main.assets.get(Assets.heavy_portrait), 0, 0, 96, 96, 0, 0, 128, 128, false, true);
        Font.stats.draw(Main.batch, "Score: " + score, 96, 16);
        Font.stats.draw(Main.batch, "Health: " + health, 96, 48);
        Font.stats.draw(Main.batch, "$" + money, 96, 80);
    }

    public boolean isInvulnerable() {
        return invulnLeft > 0;
    }

    public void gameOver() {

    }

    public void update(float delta) {

    }

    public IntVector findNewFoodLocation() {
        IntVector pos = getRandomCell();
        while (snake.occupiesPosition(pos)) {
            pos = getRandomCell();
        }
        return pos;
    }

    public IntVector getRandomCell() {
        return new IntVector(Util.randint(0, Constants.GRID_WIDTH), Util.randint(0, Constants.GRID_HEIGHT));
    }

    public static IntVector getDimVector() {
        return new IntVector(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                if (direction != SnakeDirection.DOWN) {
                    nextDirection = SnakeDirection.UP;
                }
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                if (direction != SnakeDirection.UP) {
                    nextDirection = SnakeDirection.DOWN;
                }
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                if (direction != SnakeDirection.RIGHT) {
                    nextDirection = SnakeDirection.LEFT;
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                if (direction != SnakeDirection.LEFT) {
                    nextDirection = SnakeDirection.RIGHT;
                }
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
