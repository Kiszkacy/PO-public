package agh.ics.oop;

import java.util.Arrays;

public class World {

    static public void main(String[] args) {
        Animal animal = new Animal();
        MoveDirection[] orders = OptionsParser.parse(args);
        for(MoveDirection ord : orders) {
            animal.move(ord);
        }
        System.out.println(animal.toString());
    }
}
