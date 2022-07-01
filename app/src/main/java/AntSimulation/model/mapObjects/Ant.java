package AntSimulation.model.mapObjects;

import AntSimulation.model.mapObjects.utils.Agent;
import AntSimulation.model.mapObjects.utils.Direction;
import AntSimulation.model.mapObjects.utils.Position;
import AntSimulation.model.mapObjects.utils.AntState;
import AntSimulation.utils.RandomGenerator;

public class Ant extends Agent {
    private AntState state;
    private final Colony colony;
    private Direction direction;

    Ant(Position pos, Colony colony) {
        super(pos);
        state = AntState.SEARCHING;
        direction = new Direction(RandomGenerator.getRandomDirection());
        this.colony = colony;
    }

    public AntState getStatus() {
        return state;
    }

    public void setStatus(AntState state) {
        this.state = state;
    }

    public Colony getColony() {
        return colony;
    }

    public void changeDirection(double offset) {
        direction.changeDirection(offset);
    }

    public void setDirection(Double direction) {
        this.direction = new Direction(direction);
    }

    public double getDirection() {
        return direction.toDouble();
    }
}
