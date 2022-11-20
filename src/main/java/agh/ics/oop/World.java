package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import java.util.Arrays;

public class World {

    static public void main(String[] args) {
        try {
            Application.launch(App.class, args);
        } catch (Exception e) {
            System.out.println("Application error: "+e.getMessage());
        }
    }
}
