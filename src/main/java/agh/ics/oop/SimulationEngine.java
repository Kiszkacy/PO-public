package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final IWorldMap map;
    private final MoveDirection[] moves;
    private List<Animal> anims = new ArrayList<>();

    @Override
    public void run() {
        int i = 0;
        for(MoveDirection m : moves) {
            anims.get(i).move(m);
            i = (i + 1) % anims.size();
        }
    }

    public SimulationEngine(MoveDirection[] orders, IWorldMap map, Vector2d[] initialPos) {
        this.map = map;
        moves = orders;
        for(Vector2d v : initialPos) {
            Animal anim = new Animal(v, map);
            map.place(anim);
            anims.add(anim);
        }
    }
}
