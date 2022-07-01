package AntSimulation.model.mapObjects;

import AntSimulation.model.mapObjects.utils.MapObject;
import AntSimulation.model.mapObjects.utils.Position;
import AntSimulation.model.mapObjects.utils.PheromoneType;

public class Pheromone extends MapObject {
    private PheromoneType type;

    private double intensity;

    public Pheromone(Position position, PheromoneType type) {
        super(position);
        this.type = type;
        intensity = 100.d;
    }

    public PheromoneType getType() {
        return type;
    }

    public void setType(PheromoneType type) {
        this.type = type;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
