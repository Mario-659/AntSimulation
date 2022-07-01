package AntSimulation.model;

import AntSimulation.model.mapObjects.Ant;
import AntSimulation.model.mapObjects.Colony;
import AntSimulation.model.mapObjects.Food;
import AntSimulation.model.mapObjects.Pheromone;
import AntSimulation.model.mapObjects.utils.MapObject;
import AntSimulation.model.mapObjects.utils.Position;

public class Map {
    private final MapObjectContainer<Ant> ants = new MapObjectContainer<>();
    private final MapObjectContainer<Colony> colonies = new MapObjectContainer<>();
    private final MapObjectContainer<Food> food = new MapObjectContainer<>();

    private final MapObjectContainer<Pheromone> pheromones = new MapObjectContainer<>();
    private final double maxHeight, maxWidth;

    public Map(double maxHeight, double maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public void addColony(Position position, int numOfAnts) {
        Colony colony = new Colony(position);
        colonies.add(colony);
        for (int i=0; i<numOfAnts; i++) ants.add(colony.generateAnt());
    }

    public void addFood(Position position) {
        food.add(new Food(position));
    }

    public MapObjectContainer<Ant> getAnts() {
        return ants;
    }

    public MapObjectContainer<Colony> getColonies() {
        return colonies;
    }

    public MapObjectContainer<Food> getFood() {
        return food;
    }

    public MapObjectContainer<Pheromone> getPheromones() {
        return pheromones;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public double getMaxWidth() {
        return maxWidth;
    }
}
