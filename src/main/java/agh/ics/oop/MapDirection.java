package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public MapDirection next() {
        switch (this) {
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            case EAST: return SOUTH;
        }
        return NORTH;
    }


    public MapDirection previous() {
        switch (this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
        }
        return NORTH;
    }


    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case EAST: return new Vector2d(1,0);
        }
        return new Vector2d(0,0);
    }


    public String toString() {
        switch (this) {
            case NORTH: return "N";
            case SOUTH: return "S";
            case WEST: return "W";
            case EAST: return "E";
        }
        return "";
    }
}
