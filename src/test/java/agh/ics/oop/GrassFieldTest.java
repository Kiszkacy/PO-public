package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void test() {
        Vector2d[][] placePos = {
                {new Vector2d(2,2), new Vector2d(3,4), new Vector2d(2,2)},
                {new Vector2d(0,0)},
                {new Vector2d(9,2), new Vector2d(2,9), new Vector2d(-1,0), new Vector2d(0,-1), new Vector2d(7,7)},
                {new Vector2d(1,1), new Vector2d(3,3), new Vector2d(5,1), new Vector2d(9,0), new Vector2d(3,3)},
        };
        Vector2d[][] testPos = {
                {new Vector2d(2,2), new Vector2d(3,4), new Vector2d(3,3), new Vector2d(13,13)},
                {new Vector2d(0,0), new Vector2d(0,1)},
                {new Vector2d(9,2), new Vector2d(2,9), new Vector2d(-1,0), new Vector2d(0,-1), new Vector2d(7,7), new Vector2d(3,3)},
                {new Vector2d(1,1), new Vector2d(3,3), new Vector2d(5,1), new Vector2d(9,0), new Vector2d(3,3), new Vector2d(4,4)},
        };
        IWorldMap[] map = {
                new GrassField(0),
                new GrassField(0),
                new GrassField(0),
                new GrassField(0),
        };
        boolean[][] placeSol = {
                {true, true, false},
                {true},
                {true, true, true, true, true},
                {true, true, true, true, false}
        };
        boolean[][] canMoveToSol = {
                {false, false, true, true},
                {false, true},
                {false, false, false, false, false, true},
                {false, false, false, false, false, true}
        };
        boolean[][] isOccupiedSol = {
                {true, true, false, false},
                {true, false},
                {true, true, true, true, true, false},
                {true, true, true, true, true, false}
        };
        boolean[][] objectAtSol = {
                {true, true, false, false},
                {true, false},
                {true, true, true, true, true, false},
                {true, true, true, true, true, false}
        };

        for(int i = 0; i < map.length; i++) {
            IWorldMap map_ = map[i];
            // place tests
            for(int j = 0; j < placePos[i].length; j++)
                assertEquals(placeSol[i][j], map_.place(new Animal(placePos[i][j], map_)));
            // canMoveTo tests
            for(int j = 0; j < canMoveToSol[i].length; j++)
                assertEquals(canMoveToSol[i][j], map_.canMoveTo(testPos[i][j]));
            // isOccupied tests
            for(int j = 0; j < isOccupiedSol[i].length; j++)
                assertEquals(isOccupiedSol[i][j], map_.isOccupied(testPos[i][j]));
            // objectAt tests
            for(int j = 0; j < objectAtSol[i].length; j++)
                assertEquals(objectAtSol[i][j], map_.objectAt(testPos[i][j]) != null);
        }
    }
}
