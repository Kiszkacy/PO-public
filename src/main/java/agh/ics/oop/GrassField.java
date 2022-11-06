package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    private List<Grass> grass = new LinkedList<>();
    private int grassCount;


    private boolean placeGrass(Vector2d at) {
        if (isOccupied(at)) return false;
        grass.add(new Grass(at));
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
    public boolean place(Animal animal) {
        Object at = objectAt(animal.getPosition());
        if (at instanceof Grass) {
            Grass g = (Grass)at;
            grass.remove(g);
            anims.add(animal);
            plantGrass(1);
            return true;
        }
        return super.place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Grass g : grass)
            if (g.isAt(position)) return true;
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Grass g : grass)
            if (g.isAt(position)) return g;
        return super.objectAt(position);
    }

    @Override
    public void moveNotify(Object obj) {
        for(Animal a : anims) {
            if (!(a.equals(obj))) continue;
            // animal found
            for (Grass g : grass) {
                if (!(g.isAt(a.getPosition()))) continue;
                // grass at that pos found
                grass.remove(g);
                plantGrass(1);
                break;
            }
            break;
        }
    }

    @Override
    public Pair<Vector2d, Vector2d> getMapCorners() {
        Vector2d upright = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d downleft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(Animal a : anims) {
            upright = a.getPosition().upperRight(upright);
            downleft = a.getPosition().lowerLeft(downleft);
        }
        for(Grass g : grass) {
            upright = g.getPosition().upperRight(upright);
            downleft = g.getPosition().lowerLeft(downleft);
        }

        return new Pair<>(downleft, upright);
    }


    public GrassField(int count) {
        grassCount = count;
        plantGrass(count);
    }
}
