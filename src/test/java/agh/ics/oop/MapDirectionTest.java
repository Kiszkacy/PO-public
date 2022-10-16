package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection[] key = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        for(int i = 0; i < key.length; i++)
            assertEquals(key[(i+1) % key.length], key[i].next());
    }

    @Test
    void previous() {
        MapDirection[] key = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        for(int i = 0; i < key.length; i++)
            assertEquals(key[(i+3) % key.length], key[i].previous());
    }
}