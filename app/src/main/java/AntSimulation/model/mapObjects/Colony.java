package AntSimulation.model.mapObjects;

import AntSimulation.model.mapObjects.utils.Agent;
import AntSimulation.model.mapObjects.utils.Position;

import java.util.concurrent.atomic.AtomicInteger;

public class Colony extends Agent {
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private final int id;

    public Colony(Position position) {
        super(position);
        id = idCounter.getAndIncrement();
    }

    public Ant generateAnt() {
        Position colonyPosition = getPosition();
        return new Ant(new Position(colonyPosition.x, colonyPosition.y), this);
    }

    public int getId() {
        return id;
    }
}
