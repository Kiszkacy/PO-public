package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    private int grassCount;


    private boolean placeGrass(Vector2d at) {
        if (isOccupied(at)) return false;
        Grass g = new Grass(at);
        objs.put(at, g);
        boundary.put(at, g);
        return true;
    }


    private void plantGrass(int count) {
        int i = 0;
        while (i < count) {
            Vector2d pos = new Vector2d((int)Math.sqrt(Math.random()*(grassCount)*(10+1)),
                                        (int)Math.sqrt(Math.random()*(grassCount)*(10+1)));
            if (placeGrass(pos)) i++;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        AbstractWorldMapElement obj = objs.get(position);
        return obj == null || obj instanceof Grass;
    }

    @Override
    public boolean place(Animal animal) {
        Object at = objectAt(animal.getPosition());
        boolean removedGrass = false;
        if (at instanceof Grass) {
            Grass g = (Grass)at;
            objs.remove(g.getPosition());
            removedGrass = true;
        }
        super.place(animal);
        if (removedGrass) plantGrass(1);
        return true;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement obj = objs.get(oldPosition);
        objs.remove(oldPosition);
        // check for grass at new pos
        Object targetObj = objs.get(newPosition);
        if (targetObj != null && targetObj instanceof Grass) {
            Grass g = (Grass)targetObj;
            plantGrass(1);
        }
        objs.put(newPosition, obj);
    }


    public GrassField(int count) {
        grassCount = count;
        plantGrass(count);
    }
}
