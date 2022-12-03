package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application implements IPositionChangeObserver {

    private AbstractWorldMap map;
    private SimulationEngine engine;
    private final int tileSize = 35;
    private final int moveDelay = 600;
    private Thread engineThread;
    private final GridPane grid = new GridPane();
    private final TextField text = new TextField();

    @Override
    public void init() {
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)}; //new Vector2d(3,4)};
        this.engine = new SimulationEngine(map, positions, this.moveDelay);
        this.engineThread = new Thread(this.engine);
        for (Animal a : this.engine.getAnims()) {
            a.addObserver(this);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        grid.setPadding(new Insets(5));
        grid.setGridLinesVisible(true);

        Button button = new Button("Start");
        button.setOnAction(action -> startSimulation());
        VBox vbox = new VBox(button, this.text);
        HBox hbox = new HBox(this.grid, vbox);

        drawFrame(true);
        drawContent();

        Scene scene = new Scene(hbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void startSimulation() {
        String[] args = this.text.getCharacters().toString().split("\\s+");
        this.text.clear();
        this.engine.setMoves(OptionsParser.parse(args));
        this.engineThread = new Thread(this.engine);
        this.engineThread.start();
    }


    public void drawFrame(boolean firstTime) {
        Pair<Vector2d, Vector2d> mapCorners = this.map.getMapCorners();
        Vector2d mapSize = mapCorners.second().substract(mapCorners.first());
        int offx = mapCorners.first().x;
        int offy = mapCorners.first().y;
        // setting up grid labels
        // corner
        Label label = new Label("y\\x");
        GridPane.setHalignment(label, HPos.CENTER);
        grid.add(label, 0,0, 1,1);
        if (firstTime) grid.getRowConstraints().add(new RowConstraints(this.tileSize));
        if (firstTime) grid.getColumnConstraints().add(new ColumnConstraints(this.tileSize));
        // first row/column
        for(int y = 0; y < mapSize.y + 1; y++) {
            if (firstTime) grid.getRowConstraints().add(new RowConstraints(this.tileSize));
            label = new Label(Integer.toString(offy+mapSize.y-y));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0,y+1, 1,1);
        }
        for(int x = 0; x < mapSize.x + 1; x++) {
            if (firstTime) grid.getColumnConstraints().add(new ColumnConstraints(this.tileSize));
            label = new Label(Integer.toString(offx+x));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, x+1,0, 1,1);
        }
    }

    public void drawContent() {
        Pair<Vector2d, Vector2d> mapCorners = this.map.getMapCorners();
        Vector2d mapSize = mapCorners.second().substract(mapCorners.first());
        int offx = mapCorners.first().x;
        int offy = mapCorners.first().y;

        // adding objects to map
        for(int y = 0; y < mapSize.y + 1; y++) {
            for(int x = 0; x < mapSize.x + 1; x++) {
                Vector2d v = new Vector2d(x+offx, y+offy);
                if (map.isOccupied(v)) {
                    IMapElement elem = (IMapElement)map.objectAt(v);
                    GuiElementBox box = new GuiElementBox(elem);
                    GridPane.setHalignment(box.get(), HPos.CENTER);
                    grid.add(box.get(), x+1,mapSize.y-y+1, 1,1);
                }
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            Node node = grid.getChildren().get(0);
            grid.getChildren().clear();
            grid.getChildren().add(0,node);

            drawFrame(false);
            drawContent();
        });
    }
}
