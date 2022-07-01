package AntSimulation.model.mapObjects.utils;

public abstract class MapObject {
    private Position position;

    public MapObject(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
