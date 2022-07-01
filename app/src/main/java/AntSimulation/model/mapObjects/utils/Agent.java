package AntSimulation.model.mapObjects.utils;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Agent extends MapObject {
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    public Agent(Position position) {
        super(position);
        id = idCounter.getAndIncrement();
    }

    private final int id;

    public int getId() {
        return id;
    }
}
