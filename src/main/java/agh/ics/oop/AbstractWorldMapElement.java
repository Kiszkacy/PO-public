package agh.ics.oop;

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
    public String toString() {
        return "X";
    }
}
