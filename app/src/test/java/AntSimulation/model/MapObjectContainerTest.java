package AntSimulation.model;

import AntSimulation.model.mapObjects.utils.MapObject;
import AntSimulation.model.mapObjects.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapObjectContainerTest {
    private MapObjectContainer<MapObject> container;

    private MapObject getObject(Position position) {
        return Mockito.mock(MapObject.class, Mockito.withSettings()
                .useConstructor(position)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    private MapObject getObject(double x, double y) {
        return getObject(new Position(x, y));
    }

    @BeforeEach
    void setUp() {
        container = new MapObjectContainer<>();
    }

    @Test
    void shouldIncrementSizeWhenAddOne() {
        assertEquals(0, container.getSize());

        var obj = getObject(0, 0);
        container.add(obj);

        assertEquals(1, container.getSize());
    }

    @Test
    void shouldReturnObjectWhenSizeIsOneAndTheSamePosition() {
        var obj = getObject(0, 0);
        container.add(obj);

        assertEquals(obj, container.getClosest(obj.getPosition()));
    }

    @Test
    void shouldReturnObjectWhenSizeIsOneAndDiffPosition() {
        var obj = getObject(0, 0);
        container.add(obj);

        var pos = new Position(10.d, -10.d);
        assertEquals(obj, container.getClosest(pos));
    }

    @Test
    void shouldReturnClosestWhenManyInside() {
        List<MapObject> objects = new LinkedList<>();
        objects.add(getObject(11, 78));
        objects.add(getObject(-123.892, 98.12));
        objects.add(getObject(63.93, 38));
        objects.add(getObject(90, 199));
        objects.add(getObject(-156, -12.389));
        objects.add(getObject(-89, 20.9302));

        for (var o : objects) container.add(o);

        var pos = new Position(-100.d, 30.d);
        assertEquals(-89, container.getClosest(pos).getPosition().x);
        assertEquals(20.9302, container.getClosest(pos).getPosition().y);
    }
}