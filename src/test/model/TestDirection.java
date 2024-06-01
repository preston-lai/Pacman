package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// a class to test the direction class
public class TestDirection {
    Direction testDirection;

    @Test
    public void testUP() {
        Position initialPosition = new Position(0, 0);
        Position expectedUpdatedPosition = new Position(0, -1);
        Position calculatePosition = testDirection.UP.movePos(initialPosition);
        assertEquals(expectedUpdatedPosition.getX(), calculatePosition.getX());
        assertEquals(expectedUpdatedPosition.getY(), calculatePosition.getY());
    }

    @Test
    public void testDOWN() {
        Position initialPosition = new Position(0, 0);
        Position expectedUpdatedPosition = new Position(0, 1);
        Position calculatePosition = testDirection.DOWN.movePos(initialPosition);
        assertEquals(expectedUpdatedPosition.getX(), calculatePosition.getX());
        assertEquals(expectedUpdatedPosition.getY(), calculatePosition.getY());
    }

    @Test
    public void testRIGHT() {
        Position initialPosition = new Position(0, 0);
        Position expectedUpdatedPosition = new Position(1, 0);
        Position calculatePosition = testDirection.RIGHT.movePos(initialPosition);
        assertEquals(expectedUpdatedPosition.getX(), calculatePosition.getX());
        assertEquals(expectedUpdatedPosition.getY(), calculatePosition.getY());
    }

    @Test
    public void testLEFT() {
        Position initialPosition = new Position(0, 0);
        Position expectedUpdatedPosition = new Position(-1, 0);
        Position calculatePosition = testDirection.LEFT.movePos(initialPosition);
        assertEquals(expectedUpdatedPosition.getX(), calculatePosition.getX());
        assertEquals(expectedUpdatedPosition.getY(), calculatePosition.getY());
    }
}
