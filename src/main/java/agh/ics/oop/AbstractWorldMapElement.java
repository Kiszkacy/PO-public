package agh.ics.oop;

import java.util.Objects;

// dodanie nie jest celowe, ale zmniejsza ilosc powtarzanego kodu, zwieksza czytelnosc
// i dodaje szablon dla przyszlych klas
public abstract class AbstractWorldMapElement implements IMapElement {

    protected Vector2d pos;

    @Override
    public Vector2d getPosition() {
        return this.pos;
    }

    @Override
    public boolean isAt(Vector2d pos) {
        return this.pos.equals(pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos.x, pos.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        AbstractWorldMapElement o = (AbstractWorldMapElement) obj;
        return (this.pos.equals(o.pos));
    }

    @Override
    public String toString() {
        return "X";
    }
}
