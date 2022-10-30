package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    @Test
    void posTest() {
        String[] args = {
                "f b r l f f r r f f f f f f f f",
                "f f f f f f f",
                "f l f  l f f  f f r  l f f  f f f  r l r  f f f  b b f",
                "l f f  f f f  f l b  f f b  r l b  f f b  b f b",
        };
        Vector2d[][] startPos = {
                {new Vector2d(2,2), new Vector2d(3,4)},
                {new Vector2d(0,0)},
                {new Vector2d(2,2), new Vector2d(4,3), new Vector2d(1,6)},
                {new Vector2d(4,3), new Vector2d(2,2), new Vector2d(1,6)},
        };
        Vector2d[][] endPos = {
                {new Vector2d(2,0), new Vector2d(3,4)},
                {new Vector2d(0,4)},
                {new Vector2d(0,2), new Vector2d(1,3), new Vector2d(3,5)},
                {new Vector2d(1,2), new Vector2d(1,3), new Vector2d(1,4)},
        };
        IWorldMap[] map = {
                new RectangularMap(10, 5),
                new RectangularMap(10, 5),
                new RectangularMap(8, 8),
                new RectangularMap(8, 8),
        };

        for(int i = 0; i < args.length; i++) {
            IWorldMap map_ = map[i];
            MoveDirection[] dirs = OptionsParser.parse(args[i].split(" "));
            Vector2d[] pos = startPos[i];
            IEngine engine = new SimulationEngine(dirs, map_, pos);
            engine.run();
            for(int j = 0; j < startPos[i].length; j++)
                assertTrue(map_.isOccupied(endPos[i][j]));
        }
    }
}