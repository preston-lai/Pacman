package model;

import java.awt.*;

// a class to represent the coins spread out throughout the maze
public class Coin {
    private Position coinPosition;
    public static final Color COLOR = new Color(33, 194, 33);
    private int coinX;
    private int coinY;

    // EFFECTS: coin is positioned at coordinates (x, y)
    public Coin(int x, int y) {
        this.coinX = x;
        this.coinY = y;
        this.coinPosition = new Position(this.coinX, this.coinY);
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes the position of coin
    public void setCoinPosition(Position pos) {
        this.coinPosition = pos;
    }

    public Position getCoinPosition() {
        return this.coinPosition;
    }

    public int getCoinX() {
        return this.coinPosition.getX();
    }

    public int getCoinY() {
        return this.coinPosition.getY();
    }

}
