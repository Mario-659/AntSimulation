package AntSimulation.simulation;

import AntSimulation.model.mapObjects.Ant;
import AntSimulation.model.Map;
import AntSimulation.model.mapObjects.Food;
import AntSimulation.model.mapObjects.Pheromone;
import AntSimulation.model.mapObjects.utils.AntState;
import AntSimulation.model.mapObjects.utils.PheromoneType;
import AntSimulation.model.mapObjects.utils.Position;
import AntSimulation.utils.Coordinates;
import AntSimulation.utils.RandomGenerator;

// TODO think of better solution to move ants based on their status
public class Simulation {
    private final double ANT_RANGE_VISIBILITY = 2.d;
    private final double MAX_RAND_ANGLE_CHANGE = 2.d;
    private final double ANT_MOVEMENT_RANGE = 1;
    private final double ANT_FOOD_REACH = 1.d;

    private final Map map = new Map(1000, 1000);

    private int leavePheromoneCounter = 10;
    private int decreaseIntensityCounter = 10;

    public void nextIteration() {
        handlePheromones();
        moveAnts();
    }

    private void moveAnts() {
        for (Ant ant : map.getAnts())
            moveAnt(ant);
    }

    private void handlePheromones() {
        leavePheromones();
        decreasePheromoneIntensity();
    }

    private void decreasePheromoneIntensity() {
        if (decreaseIntensityCounter == 0) {
            var iterator = map.getPheromones().iterator();
            while (iterator.hasNext()) {
                var p = iterator.next();
                p.setIntensity(p.getIntensity() - 10);
                if (p.getIntensity() <= 0) iterator.remove();
            }
            decreaseIntensityCounter = 10;
        }
        else
            decreaseIntensityCounter--;
    }

    private void leavePheromones() {
        if (leavePheromoneCounter == 0) {
            for (var a : map.getAnts()) leavePheromone(a);
            leavePheromoneCounter = 10;
        }
        else
            leavePheromoneCounter--;
    }

    private void moveAnt(Ant ant) {
        changeAntsDirection(ant);
        moveAntForward(ant);
        takeFood(ant);
    }


    private void changeAntsDirection(Ant ant) {
        if (ant.getStatus() == AntState.SEARCHING) {
            // get the closest food
            Food food = map.getFood().getClosest(ant.getPosition(), ANT_RANGE_VISIBILITY);

            if (food == null)
                // no food on map or out of ants sight range == random change in direction
                ant.changeDirection(RandomGenerator.getRandom(-1 * MAX_RAND_ANGLE_CHANGE, MAX_RAND_ANGLE_CHANGE));

            else
                // change direction to marker
                ant.setDirection(Coordinates.getDirection(ant.getPosition(), food.getPosition()));
        }
        else if (ant.getStatus() == AntState.COMING_HOME) {
            // TODO implement
        }
    }

    private void moveAntForward(Ant ant) {
        Position offset = Coordinates.getOffset(ant.getDirection(), ANT_MOVEMENT_RANGE);
        Position pos = ant.getPosition();
        pos.x += offset.x;
        pos.y += offset.y;
        ant.setPosition(pos);
    }

    private void leavePheromone(Ant ant) {
        var pos = ant.getPosition();
        PheromoneType type;

        if (ant.getStatus() == AntState.SEARCHING)
            type = PheromoneType.TO_FOOD;
        else
            type = PheromoneType.TO_HOME;

        var pheromone = new Pheromone(new Position(pos.x, pos.y),
                                        type);

        map.getPheromones().add(pheromone);
    }

    private void takeFood(Ant ant) {
        var food = map.getFood().getClosest(ant.getPosition(), ANT_FOOD_REACH);
        if (food == null) return;

        map.getFood().remove(food);
        ant.setStatus(AntState.COMING_HOME);
        // TODO add changing direction (+180 degrees?)
    }

    public Map getMap() {
        return map;
    }
}
