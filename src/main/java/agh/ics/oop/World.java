package agh.ics.oop;

import java.util.Arrays;

public class World {

    static public void run(Direction[] dirs) {
        int i = 0;
        for(Direction dir: dirs) {
            switch (dir) {
                case FORWARD:
                    System.out.print("Zwierzak idzie do przodu"); break;
                case BACKWARD:
                    System.out.print("Zwierzak idzie do tylu"); break;
                case RIGHT:
                    System.out.print("Zwierzak skreca w prawo"); break;
                case LEFT:
                    System.out.print("Zwierzak skreca w lewo"); break;
                default:
                    break;
            }
            i += 1;
            if (i != dirs.length) System.out.println(",");
        }
        System.out.println();
    }

    static private Direction[] read(String[] args) {
        Direction[] dirs = new Direction[args.length];
        int i = 0;
        for(String chr: args) {
            switch(chr.toLowerCase()) {
                case "f":
                    dirs[i] = Direction.FORWARD; break;
                case "b":
                    dirs[i] = Direction.BACKWARD; break;
                case "r":
                    dirs[i] = Direction.RIGHT; break;
                case "l":
                    dirs[i] = Direction.LEFT; break;
                default:
                    i -= 1; break;
            }
            i += 1;
        }

        return Arrays.copyOfRange(dirs, 0, i);
    }

    static public void main(String[] args) {
        System.out.println("Start");
        run(read(args));
        System.out.println("Stop");
    }
}
