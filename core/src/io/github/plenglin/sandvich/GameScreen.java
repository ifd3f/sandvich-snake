package io.github.plenglin.sandvich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.plenglin.sandvich.food.Food;
import io.github.plenglin.sandvich.food.Sandvich;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GameScreen implements Screen, InputProcessor {

    SnakeDirection direction, nextDirection;
    SpriteBatch batch;
    Texture img;
    OrthographicCamera gridCamera;
    SnakeCell snake;
    List<Food> food;
    float timeToNextUpdate;
    int lengthToGrow;
    int score;

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        food = new ArrayList<Food>();
        gridCamera = new OrthographicCamera(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        gridCamera.setToOrtho(true);
        batch = new SpriteBatch();
        snake = new SnakeCell(getRandomCell(), null);
        direction = SnakeDirection.STOP;
        nextDirection = direction;
        timeToNextUpdate = 0;
        lengthToGrow = 0;
    }

    @Override
    public void render(float delta) {

        // Update
        timeToNextUpdate -= delta;
        if (timeToNextUpdate <= 0) {
            if (lengthToGrow > 0) {
                snake.move(direction.vec, true);
                lengthToGrow--;
            } else {
                snake.move(direction.vec, false);
            }
            if (snake.isEatingSelf()) {
                Gdx.app.exit();
            }
            Food toRemove = null;
            for (Food f: food) {
                if (snake.getPosition().equals((f.getPosition()))) {
                    toRemove = f;
                    lengthToGrow += Constants.FOOD_GROW_LENGTH;
                    break;
                }
            }
            food.remove(toRemove);
            timeToNextUpdate = Constants.UPDATE_SPEED;
            direction = nextDirection;
        }
        if (food.size() < Constants.EXISTING_FOOD) {
            food.add(new Sandvich(findNewFoodLocation()));
        }

        // Render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(gridCamera.combined);
        batch.begin();
        for (Food f: food) {
            f.draw(batch, Constants.CELL_WIDTH, Constants.CELL_HEIGHT);
        }
        snake.draw(batch, Constants.CELL_WIDTH, Constants.CELL_HEIGHT);
        batch.end();
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
            case Input.Keys.W:case Input.Keys.UP:
                if (direction != SnakeDirection.DOWN) {
                    nextDirection = SnakeDirection.UP;
                }
                break;
            case Input.Keys.S:case Input.Keys.DOWN:
                if (direction != SnakeDirection.UP) {
                    nextDirection = SnakeDirection.DOWN;
                }
                break;
            case Input.Keys.A:case Input.Keys.LEFT:
                if (direction != SnakeDirection.RIGHT) {
                    nextDirection = SnakeDirection.LEFT;
                }
                break;
            case Input.Keys.D:case Input.Keys.RIGHT:
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
