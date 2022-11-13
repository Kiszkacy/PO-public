package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    private int grassCount;


    private boolean placeGrass(Vector2d at) {
        if (isOccupied(at)) return false;
        objs.put(at, new Grass(at));
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
        if (at instanceof Grass) {
            Grass g = (Grass)at;
            objs.remove(g);
            objs.put(animal.getPosition(), animal);
            plantGrass(1);
            return true;
        }
        return super.place(animal);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement obj = objs.get(oldPosition);
        objs.remove(oldPosition);
        // check for grass at new pos
        Object targetObj = objs.get(newPosition);
        if (targetObj != null && targetObj instanceof Grass) {
            Grass g = (Grass)targetObj;
            objs.remove(newPosition);
            plantGrass(1);
        }
        objs.put(newPosition, obj);
    }

    @Override
    public Pair<Vector2d, Vector2d> getMapCorners() {
        Vector2d upright = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d downleft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(AbstractWorldMapElement o : objs.values()) {
            upright = o.getPosition().upperRight(upright);
            downleft = o.getPosition().lowerLeft(downleft);
        }

        return new Pair<>(downleft, upright);
    }


    public GrassField(int count) {
        grassCount = count;
        plantGrass(count);
    }
}
