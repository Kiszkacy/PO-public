package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void walk() {
        // predefined tests
        // consts
        MoveDirection[] move = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT};
        String[] args = {
                "f FORWARD Left L foRWaRd f",
                "h f f input f f wrong f b b",
                "right f f f f random b",
                "r r f word l f f b r r r f",
                "l f r p f l f g r f l g l b",
                "left wrong forward right forward left forward right f left l wrong b",
                "B B left f f l f",
                "r f f f l f f f f",
        };
        MoveDirection[][] parseResult = {
                {move[0], move[0], move[3], move[3], move[0], move[0]},
                {move[0], move[0], move[0], move[0], move[0], move[2], move[2]},
                {move[1], move[0], move[0], move[0], move[0], move[2]},
                {move[1], move[1], move[0], move[3], move[0], move[0], move[2], move[1], move[1], move[1], move[0]},
                {move[3], move[0], move[1], move[0], move[3], move[0], move[1], move[0], move[3], move[3], move[2]},
                {move[3], move[0], move[1], move[0], move[3], move[0], move[1], move[0], move[3], move[3], move[2]},
                {move[2], move[2], move[3], move[0], move[0], move[3], move[0]},
                {move[1], move[0], move[0], move[0], move[3], move[0], move[0], move[0], move[0]}
        };
        Vector2d[] sol = {
                new Vector2d(2,2),
                new Vector2d(2,2),
                new Vector2d(3,2),
                new Vector2d(3,2),
                new Vector2d(0,4),
                new Vector2d(0,4),
                new Vector2d(0,0),
                new Vector2d(4,4)
        };

        // testing
        for(int i = 0; i < args.length; i++) {
            Animal anim = new Animal();
            MoveDirection[] parse = OptionsParser.parse(args[i].split(" "));
            assertArrayEquals(parseResult[i], parse);

            for (MoveDirection dir : parse) anim.move(dir);
            assertTrue(anim.isAt(sol[i]));
        }


        // random tests
        // consts
        Vector2d[] moveAngle = {new Vector2d(0,1), new Vector2d(1,0), new Vector2d(0,-1), new Vector2d(-1,0)};
        int xmin = 0; int xmax = 4; int ymin = 0; int ymax = 4;
        int nTests = 100; int nOrders = 50;

        // testing
        for(int i = 0; i < nTests; i++) {
            Vector2d pos = new Vector2d(2,2);
            int angle = 0; // 0 - north | 1 - east | 2 - south | 3 - west
            Animal anim = new Animal();

            for(int j = 0; j < nOrders; j++) {
                int order = (int)(Math.random()*4); // 0 - forward | 1 - right, 2 - backward | 3 - left
                anim.move(move[order]);
                if (order == 0)         pos = pos.add(moveAngle[angle]);
                else if (order == 2)    pos = pos.add(moveAngle[(angle+2) % 4]);
                else                    {angle += order == 1 ? 1 : -1; angle = (angle+4) % 4;}
                // clamp result
                pos = new Vector2d(Math.max(xmin, Math.min(pos.x, xmax)), Math.max(ymin, Math.min(pos.y, ymax)));
            }

            assertTrue(anim.isAt(pos));
        }
    }

}