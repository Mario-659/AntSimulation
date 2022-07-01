package AntSimulation.model.mapObjects.utils;

public class Direction {
    private double direction;

    public Direction(double direction) {
        validateAngle(direction);
        this.direction = direction;
    }

    public void changeDirection(double angle) {
        validateAngle(direction);

        direction += angle;
        if (direction >= 360.d) direction -= 360.d;
        else if (direction < 0) direction += 360.d;
    }
    public double toDouble() {
        return direction;
    }

    private void validateAngle(double angle) {
        if (direction >= 360.d || direction < 0)
            throw new RuntimeException("Direction must be in range <0, 360). Given direction -> " + direction);
    }
}
