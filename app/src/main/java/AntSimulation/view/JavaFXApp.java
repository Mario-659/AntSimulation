package AntSimulation.view;

import AntSimulation.model.Map;
import AntSimulation.model.MapObjectContainer;
import AntSimulation.model.mapObjects.utils.MapObject;
import AntSimulation.model.mapObjects.utils.Position;
import AntSimulation.simulation.Simulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import static java.lang.Math.round;

public class JavaFXApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Map map;
    private StackPane root;
    private Label label;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ant Simulation");
        primaryStage.setResizable(true);

        root = new StackPane();

        Scene scene = new Scene(root, 1000, 800);

        final Label reporter = new Label();
        label = createMonitoredLabel(scene);
//        label.setTranslateY(100);
//        label.setTranslateX(10);

        primaryStage.setScene(scene);
        primaryStage.show();

        Simulation simulation = new Simulation();

        map = simulation.getMap();
        map.addColony(new Position(0, 0), 50);
        map.addFood(new Position(500, 500));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                simulation.nextIteration();
                root.getChildren().setAll(label, reporter);
                drawObjects();
            }
        }.start();
    }

    private <T extends MapObject> void drawMapObjects(MapObjectContainer<T> objects, double radius, Color color) {
        for (var object : objects) {
            Circle circle = getCircle(object.getPosition(), radius, color);
            root.getChildren().add(circle);
        }
    }

    private void drawObjects() {
        drawAnts();
        drawColonies();
        drawFood();
        drawPheromones();
    }

    private void drawPheromones() {
        drawMapObjects(map.getPheromones(), 0.5, Color.BLUEVIOLET);
    }

    private void drawAnts() {
        drawMapObjects(map.getAnts(), 2, Color.BLACK);
    }

    private void drawFood() {
        drawMapObjects(map.getFood(), 10, Color.GREEN);
    }

    private void drawColonies() {
        drawMapObjects(map.getColonies(), 10, Color.AQUA);
    }

    private Circle getCircle(Position position, double radius, Color color) {
        Circle circle = new Circle(radius);
        circle.setTranslateX(position.x);
        circle.setTranslateY(position.y);
        circle.setFill(color);
        return circle;
    }

    private Label createMonitoredLabel(Scene scene) {
        final Label reporter = new Label();
        reporter.setTranslateY(scene.getHeight() / 2 - 50);

        scene.setOnMouseMoved(event -> {
            String msg = "x: " + round(event.getX() - scene.getWidth() / 2) +
                        ", y: " + round(event.getY() - scene.getHeight() / 2);
            reporter.setText(msg);
        });
        return reporter;
    }
}
