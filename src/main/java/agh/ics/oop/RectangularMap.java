package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;


public class RectangularMap extends AbstractWorldMap {

    private Vector2d mapSize;

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!(position.precedes(mapSize) && position.follows(new Vector2d(0,0)))) return false;
        return super.canMoveTo(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (!(pos.precedes(mapSize) && pos.follows(new Vector2d(0,0)))) return false;
        return super.place(animal);
    }

    @Override
    public Pair<Vector2d, Vector2d> getMapCorners() {
        return new Pair<>(new Vector2d(0,0), this.mapSize);
    }


    public RectangularMap(int width, int height) {
        mapSize = new Vector2d(width-1, height-1);
    }


    public RectangularMap(Vector2d pos) {
        mapSize = pos;
    }
}
