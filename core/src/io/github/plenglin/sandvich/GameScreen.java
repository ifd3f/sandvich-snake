package io.github.plenglin.sandvich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.plenglin.sandvich.food.*;
import io.github.plenglin.sandvich.util.IntVector;
import io.github.plenglin.sandvich.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * The screen that the game takes place on
 */
public class GameScreen implements Screen, InputProcessor {

    SnakeDirection direction, nextDirection;
    SpriteBatch batch;
    ShapeRenderer shape;
    Texture img;
    OrthographicCamera gridCamera;
    SnakeCell snake;
    List<Food> food;
    InvulnType invulnType;
    float timeToNextUpdate, invulnLeft;
    int lengthToGrow, score, money, health;
    BitmapFont statfont;
    FoodSpawner spawner;

    enum InvulnType {
        NONE, UBER, DAMAGE
    }

    @Override
    public void show() {
        spawner = new FoodSpawner();
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Sandvich(position);
            }
        }, 20);
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Cake(position);
            }
        }, 1);
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Chocolate(position);
            }
        }, 1);
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Medkit(position);
            }
        }, 1);
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Money(position);
            }
        }, 1);
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Steak(position);
            }
        }, 1);
        spawner.addFood(new FoodDefinition() {
            @Override
            public Food create(IntVector position) {
                return new Sticky(position);
            }
        }, 1);

        FreeTypeFontGenerator.FreeTypeFontParameter fontParams = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParams.size = 20;
        fontParams.color = new Color(Color.WHITE);
        fontParams.flip = true;
        statfont = Main.assets.get(Assets.normal_font).generateFont(fontParams);

        Gdx.input.setInputProcessor(this);
        food = new ArrayList<Food>();
        gridCamera = new OrthographicCamera(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        gridCamera.setToOrtho(true);
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
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

    @Override
    public void render(float delta) {

        // Update
        List<Food> toRemove = new ArrayList<Food>();
        timeToNextUpdate -= delta;
        invulnLeft -= delta;

        if (!isInvulnerable()) {
            invulnType = InvulnType.NONE;
        }

        for (Food f: food) {
            f.update(delta);
            if (f.hasDecayed()) {
                toRemove.add(f);
            }
        }
        if (timeToNextUpdate <= 0) {
            if (lengthToGrow > 0) {
                snake.move(direction, true);
                lengthToGrow--;
            } else {
                snake.move(direction, false);
            }
            if (snake.isEatingSelf() && !isInvulnerable()) {
                health -= 250;
                invulnType = InvulnType.DAMAGE;
                invulnLeft = Constants.INVINCIBILITY_TIME;
            }
            for (Food f : food) {
                if (snake.getPosition().equals((f.getPosition()))) {
                    toRemove.add(f);
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
        for (Food f: toRemove) {
            food.remove(f);
        }
        if (food.size() < Constants.EXISTING_FOOD) {
            food.add(spawner.create(findNewFoodLocation()));
        }
        if (health <= 0) {
            gameOver();
        }

        // Render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gridCamera.combined);
        shape.setProjectionMatrix(gridCamera.combined);

        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.BLUE);
        shape.rect(Constants.GRID_OFFSET_X, Constants.GRID_OFFSET_Y, Constants.GRID_DISPLAY_WIDTH, Constants.GRID_DISPLAY_HEIGHT);
        shape.end();

        batch.begin();
        for (Food f : food) {
            f.draw(batch);
        }

        if (invulnType == InvulnType.UBER) {
            batch.setColor(Color.RED);
        }
        if (!(invulnType == InvulnType.DAMAGE && Util.squareWave(System.currentTimeMillis(), 1000))) {
            snake.draw(batch);
        }
        batch.setColor(Color.WHITE);

        batch.draw(Main.assets.get(Assets.heavy_portrait), 0, 0, 96, 96, 0, 0, 128, 128, false, true);
        statfont.draw(batch, "Score: " + score, 96, 16);
        statfont.draw(batch, "Health: " + health, 96, 48);
        statfont.draw(batch, "$" + money, 96, 80);

        batch.end();
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
