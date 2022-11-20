package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {

    static public MoveDirection[] parse(String[] args) throws IllegalArgumentException {
        MoveDirection[] res = new MoveDirection[args.length];
        int i = 0;
        for(String arg: args) {
            switch(arg.toLowerCase()) {
                case "f":
                case "forward":
                    res[i] = MoveDirection.FORWARD; break;
                case "b":
                case "backward":
                    res[i] = MoveDirection.BACKWARD; break;
                case "r":
                case "right":
                    res[i] = MoveDirection.RIGHT; break;
                case "l":
                case "left":
                    res[i] = MoveDirection.LEFT; break;
                default:
                    throw new IllegalArgumentException("'"+arg+"' is not a legal move specification");
            }
            i += 1;
        }

        return Arrays.copyOfRange(res, 0, i);
    }
}
