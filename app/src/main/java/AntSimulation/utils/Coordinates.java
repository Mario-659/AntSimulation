package AntSimulation.utils;

import AntSimulation.model.mapObjects.utils.Position;

import java.lang.Math;
import static java.lang.Math.atan2;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Coordinates {
    public static double getDistance(Position a, Position b) {
        double q1 = Math.pow((a.x - b.x), 2);
        double q2 = Math.pow((a.x - b.x), 2);
        return Math.sqrt(q1 + q2);
    }

    public static double getDirection(Position start, Position end) {
        double delta_x = start.x - end.x;
        double delta_y = start.y - end.y;
        double radians = atan2(delta_y, delta_x);
        double degrees = radians/PI * 180.d;
        if(degrees < 0) degrees += 360.d;

        return degrees;
    }

    public static Position getOffset(double direction, double distance) {
        double x = distance * cos(direction * PI / 180);
        double y = distance * sin(direction * PI / 180);
        return new Position(x, y);
    }
}
