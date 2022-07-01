package AntSimulation.model;

import AntSimulation.model.mapObjects.utils.MapObject;
import AntSimulation.model.mapObjects.utils.Position;
import AntSimulation.utils.Coordinates;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

// TODO getClosest method takes always n time, think of better solution
public class MapObjectContainer <T extends MapObject> implements Iterable<T> {
    // TODO change to synchronized structure?
    private final LinkedList<T> objects = new LinkedList<>();

    public void add(T element) {
        objects.add(element);
    }

    public T getClosest(Position position) {
        if (getSize() == 0) return null;

        T nearestObject = objects.getFirst();
        double nearestDistance = Coordinates.getDistance(nearestObject.getPosition(), position);

        for (T object : objects) {
            double distance = Coordinates.getDistance(position, object.getPosition());
            if (distance < nearestDistance) {
                nearestObject = object;
                nearestDistance = distance;
            }
        }
        return nearestObject;
    }

    public T getClosest(Position position, double minDistance) {
        var closest = getClosest(position);
        if (closest == null || Coordinates.getDistance(closest.getPosition(), position) < minDistance)
            return null;
        return closest;
    }

    public void remove(T element) {
        objects.remove(element);
    }

    public int getSize() {
        return objects.size();
    }

    @Override
    public Iterator<T> iterator() {
        return objects.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
