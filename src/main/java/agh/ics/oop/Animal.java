package agh.ics.oop;

public class Animal {
    private MapDirection dir = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2,2);


    public boolean isAt(Vector2d pos) {
        return this.pos.equals(pos);
    }


//    public boolean isLookingAt(MapDirection dir) {
//        return this.dir == dir;
//    }


    public Animal move(MoveDirection dir) {
        if (dir == MoveDirection.LEFT) {
            this.dir = this.dir.previous();
        } else if (dir == MoveDirection.RIGHT) {
            this.dir = this.dir.next();
        } else {
            int offx = (this.dir == MapDirection.EAST ? 1 : 0) + (this.dir == MapDirection.WEST ? -1 : 0);
            int offy = (this.dir == MapDirection.NORTH ? 1 : 0) + (this.dir == MapDirection.SOUTH ? -1 : 0);
            pos = dir == MoveDirection.FORWARD ? pos.add(new Vector2d(offx, offy)) : pos;
            pos = dir == MoveDirection.BACKWARD ? pos.add(new Vector2d(offx, offy).opposite()) : pos;
            // limit (clamping)
            pos = new Vector2d(Math.max(0, Math.min(pos.x, 4)), Math.max(0, Math.min(pos.y, 4)));
        }
        return this;
    }


    public String toString() {
        return String.format("pos: %s | dir: %s", pos.toString(), dir.toString());
    }
}
