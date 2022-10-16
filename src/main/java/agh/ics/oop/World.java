package agh.ics.oop;

import java.util.Arrays;

public class World {

    static public void run(MoveDirection[] dirs) {
        int i = 0;
        for(MoveDirection dir: dirs) {
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

    static private MoveDirection[] read(String[] args) {
        MoveDirection[] dirs = new MoveDirection[args.length];
        int i = 0;
        for(String chr: args) {
            switch(chr.toLowerCase()) {
                case "f":
                    dirs[i] = MoveDirection.FORWARD; break;
                case "b":
                    dirs[i] = MoveDirection.BACKWARD; break;
                case "r":
                    dirs[i] = MoveDirection.RIGHT; break;
                case "l":
                    dirs[i] = MoveDirection.LEFT; break;
                default:
                    i -= 1; break;
            }
            i += 1;
        }

        return Arrays.copyOfRange(dirs, 0, i);
    }

    static public void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }
}
