package model;

// a class to represent a characters current direction that it is facing in game
// Referenced SnakeConsole-Lanterna
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    RIGHT(1, 0),
    LEFT(-1, 0);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: moves a pos by 1 to the facing direction
    public Position movePos(Position pos) {
        return new Position(dx + pos.getX(), dy + pos.getY());
    }

}
