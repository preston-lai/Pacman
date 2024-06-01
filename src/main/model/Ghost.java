package model;

import java.awt.*;

// a class to represent the ghosts spread out throughout the maze
public class Ghost {

    public static final Color COLOR = new Color(0, 0, 255);
    // Ghost position
    private Position ghostPos;
    private Direction direction;
    public static final int DY = -2;
    private int ghostX;
    private int ghostY;
    
    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: a ghost starts at the beginning Pos of 10, 10
    // takes a ghost name
    public Ghost(int x, int y) {
        this.ghostX = x;
        this.ghostY = y;
        this.ghostPos = new Position(this.ghostX, this.ghostY);
        this.direction = Direction.RIGHT;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: moves the ghost in the direction randomly
    public void moveGhost() {
        this.ghostY = this.ghostY + DY;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes the position of Ghost
    public void setGhostPosition(Position pos) {
        this.ghostPos = pos;
    }

    public Direction getGhostDirection() {
        return this.direction;
    }

    public Position getGhostPosition() {
        return this.ghostPos;
    }

    public int getGhostX() {
        return this.ghostPos.getX();
    }

    public int getGhostY() {
        return this.ghostPos.getY();
    }
}
