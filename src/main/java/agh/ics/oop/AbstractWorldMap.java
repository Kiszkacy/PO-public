package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;


public abstract class AbstractWorldMap implements IWorldMap{

    protected List<Animal> anims = new LinkedList<>();


    @Override
    public boolean canMoveTo(Vector2d position) {
        for(Animal a : anims)
            if (a.isAt(position)) return false;
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition()))
            return false;
        anims.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal a : anims)
            if (a.isAt(position)) return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal a : anims)
            if (a.isAt(position)) return a;
        return null;
    }

    @Override
    public void moveNotify(Object obj) {
        return;
    }


    abstract public Pair<Vector2d, Vector2d> getMapCorners();

    @Override
    public String toString() {
        Pair<Vector2d, Vector2d> corners = getMapCorners();
        return new MapVisualizer(this).draw(corners.first(), corners.second());
    }
}
