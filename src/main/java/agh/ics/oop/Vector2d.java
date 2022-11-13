package agh.ics.oop;


import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;


    public boolean precedes(Vector2d v) {
        return (x <= v.x && y <= v.y);
    }


    public boolean follows(Vector2d v) {
        return (x >= v.x && y >= v.y);
    }


    public Vector2d upperRight(Vector2d v) {
        return new Vector2d(Math.max(x, v.x), Math.max(y, v.y));
    }


    public Vector2d lowerLeft(Vector2d v) {
        return new Vector2d(Math.min(x, v.x), Math.min(y, v.y));
    }


    public Vector2d add(Vector2d v) {
        return new Vector2d(x+v.x, y+v.y);
    }


    public Vector2d substract(Vector2d v) {
        return new Vector2d(x-v.x, y-v.y);
    }


    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector2d)) return false;
        Vector2d v = (Vector2d) obj;
        return (x == v.x && y == v.y);
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
    }


    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
