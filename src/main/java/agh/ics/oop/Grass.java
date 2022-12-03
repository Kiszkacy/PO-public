package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getTextureName() {
        return "grass";
    }

    public Grass(Vector2d pos) {
        this.pos = pos;
    }
}
