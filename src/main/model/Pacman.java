package model;

// a class to represent the user who plays the character Pacman
// Referenced SnakeConsole-Lanterna
// Note: code in this file has been referenced from B02-SpaceInvadersBase
public class Pacman {
    private Position position;
    private Direction direction;
    private int pacmanX;
    private int pacmanY;

    public Pacman(int x, int y) {
        this.pacmanX = x;
        this.pacmanY = y;
        this.position = new Position(this.pacmanX, this.pacmanY);
        this.direction = Direction.RIGHT;
    }

    // EFFECTS: move Pacman in the given direction
    public void movePacman() {
        this.position = direction.movePos(this.position);
        handleBoundary();
    }

    // Constrains pacman so that it doesn't travel of sides of screen
    // MODIFIES: this
    // EFFECTS: pacman is constrained within the boundaries of the game
    public void handleBoundary() {
        // X Boundary
        if (this.getPacmanX() < 0) {
            setPacmanPosition(new Position(0, this.getPacmanY()));
        } else if (this.getPacmanX() >= Game.WIDTH) {
            setPacmanPosition(new Position(Game.WIDTH - 5, this.getPacmanY()));
        }

        // Y Boundary
        if (this.getPacmanY() < 0) {
            setPacmanPosition(new Position(this.getPacmanX(), 0));
        } else if (this.getPacmanY() >= Game.HEIGHT) {
            setPacmanPosition(new Position(this.getPacmanX(), Game.HEIGHT - 5));
        }
    }

    // MODIFIES: this
    // EFFECTS: Faces pacman to the right
    public void faceRight() {
        direction = Direction.RIGHT;
    }

    // MODIFIES: this
    // EFFECTS: Faces pacman to the left
    public void faceLeft() {
        direction = Direction.LEFT;
    }

    public void faceUp() {
        direction = Direction.UP;
    }

    public void faceDown() {
        direction = Direction.DOWN;
    }


    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes the direction of Pacman
    public void setPacmanDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getPacmanDirection() {
        return this.direction;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes the position of Pacman
    public void setPacmanPosition(Position pos) {
        this.position = pos;
    }

    public Position getPacmanPosition() {
        return this.position;
    }

    public int getPacmanX() {
        return this.position.getX();
    }

    public int getPacmanY() {
        return this.position.getY();
    }

}
