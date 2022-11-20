package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, AbstractWorldMapElement> objs = new HashMap<>();
    protected MapBoundary boundary = new MapBoundary();


    @Override
    public boolean canMoveTo(Vector2d position) {
        return objs.get(position) == null;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        if (isOccupied(animal.getPosition()))
            throw new IllegalArgumentException("Can not place animal at "+ animal.getPosition().toString()+" is already occupied");
        objs.put(animal.getPosition(), animal);
        animal.addObserver(this);
        boundary.put(animal.getPosition(), animal);
        animal.addObserver(boundary);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objs.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return objs.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement obj = objs.get(oldPosition);
        objs.remove(oldPosition);
        objs.put(newPosition, obj);
    }


    public Pair<Vector2d, Vector2d> getMapCorners() {
        Vector2d upright = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d downleft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        upright = boundary.xAxis.lastKey().upperRight(upright);
        upright = boundary.yAxis.lastKey().upperRight(upright);
        downleft = boundary.xAxis.firstKey().lowerLeft(downleft);
        downleft = boundary.yAxis.firstKey().lowerLeft(downleft);

        return new Pair<>(downleft, upright);
    }

    @Override
    public String toString() {
        Pair<Vector2d, Vector2d> corners = getMapCorners();
        return new MapVisualizer(this).draw(corners.first(), corners.second());
    }
}
