package agh.ics.oop;

public class Animal {
    private MapDirection dir = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2,2);
    private IWorldMap map;

    public boolean isAt(Vector2d pos) {
        return this.pos.equals(pos);
    }


//    public boolean isLookingAt(MapDirection dir) {
//        return this.dir == dir;
//    }


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
        }
        return this;

    }


    public Vector2d getPosition() {
        return this.pos;
    }

    public String toString() {
        return String.format(dir.toString());
    }

    public Animal() {}

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPos) {
        this.map = map;
        this.pos = initialPos;
    }
}
