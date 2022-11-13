package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, AbstractWorldMapElement> objs = new HashMap<>();


    @Override
    public boolean canMoveTo(Vector2d position) {
        return objs.get(position) == null;
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition()))
            return false;
        objs.put(animal.getPosition(), animal);
        animal.addObserver(this);
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

    abstract public Pair<Vector2d, Vector2d> getMapCorners();

    @Override
    public String toString() {
        Pair<Vector2d, Vector2d> corners = getMapCorners();
        return new MapVisualizer(this).draw(corners.first(), corners.second());
    }
}
