package model;

// a class to represent a character position in the game state
public class Position {
    // Referenced SnakeConsole-Lanterna
    // Note: code in this file has been referenced from SnakeConsole-Lanterna
    private int positionX;
    private int positionY;

    public Position(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public int getX() {
        return this.positionX;
    }

    public int getY() {
        return this.positionY;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: determines if an object is equal to each other. If not equal or null, return False
    //          Otherwise returns whether a pacman position's X and Y are equal to the current node.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Position pacmanNode = (Position) o;
            return positionX == pacmanNode.positionX && positionY == pacmanNode.positionY;
        }
    }
}
