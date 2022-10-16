package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        int[] v1 = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] v2 = {0,1, 0,0, -1,1, -1,1, 5,-5, -5000,10};
        boolean[] key = {true, true, false, false, false, false};

        for(int i = 0; i < key.length; i++) {
            Vector2d v1_ = new Vector2d(v1[i*2], v1[i*2+1]);
            Vector2d v2_ = new Vector2d(v2[i*2], v2[i*2+1]);
            assertEquals(key[i], v1_.precedes(v2_));
        }
    }

    @Test
    void follows() {
        int[] v1 = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] v2 = {0,1, 0,0, -1,1, -1,1, 5,-5, -5000,10};
        boolean[] key = {false, true, false, false, true, true};

        for(int i = 0; i < key.length; i++) {
            Vector2d v1_ = new Vector2d(v1[i*2], v1[i*2+1]);
            Vector2d v2_ = new Vector2d(v2[i*2], v2[i*2+1]);
            assertEquals(key[i], v1_.follows(v2_));
        }
    }

    @Test
    void upperRight() {
        int[] v1 = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] v2 = {0,1, 0,0, -1,1, -1,1, 5,-5, -5000,10};
        int[] key = {0,1, 0,0, 0,1, 1,1, 10,-5, -1,10000};

        for(int i = 0; i < key.length; i+=2) {
            Vector2d v1_ = new Vector2d(v1[i], v1[i+1]);
            Vector2d v2_ = new Vector2d(v2[i], v2[i+1]);
            Vector2d res = new Vector2d(key[i], key[i+1]);
            assertEquals(res, v1_.upperRight(v2_));
        }
    }

    @Test
    void lowerLeft() {
        int[] v1 = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] v2 = {0,1, 0,0, -1,1, -1,1, 5,-5, -5000,10};
        int[] key = {-1,-1, 0,0, -1,0, -1,-1, 5,-5, -5000,10};

        for(int i = 0; i < key.length; i+=2) {
            Vector2d v1_ = new Vector2d(v1[i], v1[i+1]);
            Vector2d v2_ = new Vector2d(v2[i], v2[i+1]);
            Vector2d res = new Vector2d(key[i], key[i+1]);
            assertEquals(res, v1_.lowerLeft(v2_));
        }
    }

    @Test
    void add() {
        int[] v1 = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] v2 = {0,1, 0,0, -1,1, -1,1, 5,-5, -5000,10};
        int[] key = {-1,0, 0,0, -1,1, 0,0, 15,-10, -5001,10010};

        for(int i = 0; i < key.length; i+=2) {
            Vector2d v1_ = new Vector2d(v1[i], v1[i+1]);
            Vector2d v2_ = new Vector2d(v2[i], v2[i+1]);
            Vector2d res = new Vector2d(key[i], key[i+1]);
            assertEquals(res, v1_.add(v2_));
        }
    }

    @Test
    void substract() {
        int[] v1 = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] v2 = {0,1, 0,0, -1,1, -1,1, 5,-5, -5000,10};
        int[] key = {-1,-2, 0,0, 1,-1, 2,-2, 5,0, 4999,9990};

        for(int i = 0; i < key.length; i+=2) {
            Vector2d v1_ = new Vector2d(v1[i], v1[i+1]);
            Vector2d v2_ = new Vector2d(v2[i], v2[i+1]);
            Vector2d res = new Vector2d(key[i], key[i+1]);
            assertEquals(res, v1_.substract(v2_));
        }
    }

    @Test
    void opposite() {
        int[] v = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        int[] key = {1,1, 0,0, 0,0, -1,1, -10,5, 1,-10000};

        for(int i = 0; i < key.length; i+=2) {
            Vector2d v_ = new Vector2d(v[i], v[i+1]);
            Vector2d res = new Vector2d(key[i], key[i+1]);
            assertEquals(res, v_.opposite());
        }
    }

    @Test
    void testEquals() {
        int[] v = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        Object[] obj = {new Vector2d(-1,-1), 5, new Vector2d(0,0), null, "error", new Vector2d(-1, 10000)};
        boolean[] key = {true, false, true, false, false, true};

        for(int i = 0; i < key.length; i++) {
            Vector2d v_ = new Vector2d(v[i*2], v[i*2+1]);
            assertEquals(key[i], v_.equals(obj[i]));
        }
    }

    @Test
    void testToString() {
        int[] v = {-1,-1, 0,0, 0,0, 1,-1, 10,-5, -1,10000};
        String[] key = {"-1,-1", "0,0", "0,0", "1,-1", "10,-5", "-1,10000"};

        for(int i = 0; i < key.length; i++) {
            Vector2d v_ = new Vector2d(v[i*2], v[i*2+1]);
            assertEquals("("+key[i]+")", v_.toString());
        }
    }
}