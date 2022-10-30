package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;


public class RectangularMap implements IWorldMap {

    private List<Animal> anims = new LinkedList<>();
    private final Vector2d mapSize;


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!(position.precedes(mapSize) && position.follows(new Vector2d(0,0)))) return false;
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
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0,0), mapSize);
    }


    public RectangularMap(int width, int height) {
        mapSize = new Vector2d(width-1, height-1);
    }
}
