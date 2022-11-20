package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.List;

public class App extends Application {

    private AbstractWorldMap map;
    private IEngine engine;
    private final int tileSize = 25;

    @Override
    public void init() throws Exception {
        List<String> args = getParameters().getRaw();
        MoveDirection[] directions = OptionsParser.parse(args.toArray(new String[0]));
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)}; //new Vector2d(3,4)};
        this.engine = new SimulationEngine(directions, map, positions);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pair<Vector2d, Vector2d> mapCorners = this.map.getMapCorners();
        Vector2d mapSize = mapCorners.second().substract(mapCorners.first());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5));
        grid.setGridLinesVisible(true);
        int offx = mapCorners.first().x;
        int offy = mapCorners.first().y;

        // setting up grid labels
        // corner
        Label label = new Label("y\\x");
        GridPane.setHalignment(label, HPos.CENTER);
        grid.add(label, 0,0, 1,1);
        grid.getRowConstraints().add(new RowConstraints(this.tileSize));
        grid.getColumnConstraints().add(new ColumnConstraints(this.tileSize));
        // first row/column
        for(int y = 0; y < mapSize.y + 1; y++) {
            grid.getRowConstraints().add(new RowConstraints(this.tileSize));
            label = new Label(Integer.toString(offy+mapSize.y-y));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0,y+1, 1,1);
        }
        for(int x = 0; x < mapSize.x + 1; x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(this.tileSize));
            label = new Label(Integer.toString(offx+x));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, x+1,0, 1,1);
        }

        // adding objects to map
        for(int y = 0; y < mapSize.y + 1; y++) {
            for(int x = 0; x < mapSize.x + 1; x++) {
                Vector2d v = new Vector2d(x+offx, y+offy);
                if (map.isOccupied(v)) {
                    Object obj = map.objectAt(v);
                    label = new Label(obj.toString());
                    GridPane.setHalignment(label, HPos.CENTER);
                    grid.add(label, x+1,mapSize.y-y+1, 1,1);
                }
            }
        }

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
