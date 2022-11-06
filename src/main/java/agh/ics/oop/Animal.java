package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {

    private MapDirection dir = MapDirection.NORTH;
    private IWorldMap map;


    public Animal move(MoveDirection dir) {
        if (dir == MoveDirection.LEFT)
            this.dir = this.dir.previous();
        else if (dir == MoveDirection.RIGHT)
            this.dir = this.dir.next();
        else {
            int offx = (this.dir == MapDirection.EAST ? 1 : 0) + (this.dir == MapDirection.WEST ? -1 : 0);
            int offy = (this.dir == MapDirection.NORTH ? 1 : 0) + (this.dir == MapDirection.SOUTH ? -1 : 0);
            Vector2d off = new Vector2d(offx, offy);
            pos = dir == MoveDirection.FORWARD && map.canMoveTo(pos.add(off)) ? pos.add(off) : pos;
            pos = dir == MoveDirection.BACKWARD && map.canMoveTo(pos.substract(off)) ? pos.substract(off) : pos;
            map.moveNotify(this);
        }
        return this;

    }

    @Override
    public String toString() {
        return String.format(dir.toString());
    }


    public Animal() {}


    public Animal(IWorldMap map) {
        this.pos = new Vector2d(0,0);
        this.map = map;
    }


    public Animal(Vector2d initialPos, IWorldMap map) {
        this.pos = initialPos;
        this.map = map;
    }
}
