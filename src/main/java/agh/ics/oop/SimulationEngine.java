package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    private final IWorldMap map;
    private MoveDirection[] moves;
    private List<Animal> anims = new ArrayList<>();
    private int moveDelay;
    private int moveIdx = 0;
    private int animIdx = 0;

    @Override
    public void run() {
        while (moveIdx < moves.length) {
            anims.get(animIdx).move(moves[moveIdx]);
            animIdx = (animIdx + 1) % anims.size();
            moveIdx += 1;
            try {
                Thread.sleep(this.moveDelay);
            } catch (Exception e) {
                return;
            }
//            System.out.println(moveIdx);
        }
    }


    public List<Animal> getAnims() {
        return this.anims;
    }


    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
        this.moveIdx = 0;
    }


    public SimulationEngine(MoveDirection[] orders, IWorldMap map, Vector2d[] initialPos) {
        this.map = map;
        this.moves = orders;
        this.moveDelay = 300;
        for(Vector2d v : initialPos) {
            Animal anim = new Animal(v, map);
            map.place(anim);
            anims.add(anim);
        }
    }

    public SimulationEngine(MoveDirection[] orders, IWorldMap map, Vector2d[] initialPos, int moveDelay) {
        this.map = map;
        this.moves = orders;
        this.moveDelay = moveDelay;
        for(Vector2d v : initialPos) {
            Animal anim = new Animal(v, map);
            map.place(anim);
            anims.add(anim);
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] initialPos, int moveDelay) {
        this.map = map;
        this.moves = new MoveDirection[0];
        this.moveDelay = moveDelay;
        for(Vector2d v : initialPos) {
            Animal anim = new Animal(v, map);
            map.place(anim);
            anims.add(anim);
        }
    }
}
