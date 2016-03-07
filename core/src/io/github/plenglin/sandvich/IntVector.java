package io.github.plenglin.sandvich;

import com.badlogic.gdx.math.Vector2;

public class IntVector {

    public int x, y;

    public IntVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector(IntVector vec) {
        this(vec.x, vec.y);
    }

    public IntVector add(IntVector other) {
        return new IntVector(this.x + other.x, this.y + other.y);
    }

    public IntVector mod(IntVector other) {
        return new IntVector(this.x % other.x, this.y % other.y);
    }

    public IntVector sub(IntVector other) {
        return new IntVector(this.x - other.x, this.y - other.y);
    }

    @Override
    public IntVector clone() {
        return new IntVector(this);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IntVector)) {
            return false;
        }
        IntVector castOther = (IntVector) other;
        return x == castOther.x && y == castOther.y;
    }

    public Vector2 toVec2() {
        return new Vector2(x, y);
    }

    @Override
    public String toString() {
        return "IntVector<" + x + ", " + y + ">";
    }
}
