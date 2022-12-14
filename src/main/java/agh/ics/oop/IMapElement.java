package agh.ics.oop;

public interface IMapElement {

    Vector2d getPosition();

    boolean isAt(Vector2d pos);

    String getTextureName();

    String toString();
}
