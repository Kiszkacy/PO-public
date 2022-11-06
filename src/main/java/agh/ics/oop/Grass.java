package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {

    @Override
    public String toString() {
        return "*";
    }


    public Grass(Vector2d pos) {
        this.pos = pos;
    }
}
