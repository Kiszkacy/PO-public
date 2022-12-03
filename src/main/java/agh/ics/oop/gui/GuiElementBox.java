package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {

    private VBox vbox;


    public Node get() {
        return this.vbox;
    }


    public GuiElementBox(IMapElement elem) {
        try {
            Image image = new Image(new FileInputStream(String.format("src/main/resources/%s.png", elem.getTextureName())));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);

            Label label = new Label(elem.getPosition().toString());
            this.vbox = new VBox();
            this.vbox.getChildren().addAll(imageView, label);
            this.vbox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException e) {
            System.out.println("Application GUI error: "+e.getMessage());
        }
    }
}
