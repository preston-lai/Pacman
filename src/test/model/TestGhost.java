package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// a class to test the ghost class
public class TestGhost {
    Ghost g1;
    Coin c1;
    Coin c2;
    Coin c3;

    @BeforeEach
    public void runBefore() {
        g1 = new Ghost(1,1);
        c1 = new Coin(2, 2);
        c2 = new Coin(3, 3);
        c3 = new Coin(4, 4);
    }

    @Test
    public void testConstructor() {
        assertEquals(new Position(1, 1), g1.getGhostPosition());
        assertEquals(1, g1.getGhostPosition().getX());
        assertEquals(1, g1.getGhostPosition().getY());
        assertEquals(Direction.RIGHT, g1.getGhostDirection());
    }

    @Test
    public void testMoveGhostOnce() {
        g1.moveGhost();
        assertEquals(1, g1.getGhostX());
        assertEquals(1, g1.getGhostY());
        assertEquals(new Position(1, 1), g1.getGhostPosition());
        assertEquals(Direction.RIGHT, g1.getGhostDirection());
    }

    @Test
    public void testMoveGhostTwice() {
        g1.moveGhost();
        g1.moveGhost();
        assertEquals(1, g1.getGhostX());
        assertEquals(1, g1.getGhostY());
        assertEquals(new Position(1, 1), g1.getGhostPosition());
        assertEquals(Direction.RIGHT, g1.getGhostDirection());
    }

    @Test
    public void testSetGhostPosOnce() {
        g1.setGhostPosition(new Position(1, 1));
        assertEquals(1, g1.getGhostX());
        assertEquals(1, g1.getGhostY());
        assertEquals(new Position(1, 1), g1.getGhostPosition());
    }

    @Test
    public void testSetGhostPosTwice() {
        g1.setGhostPosition(new Position(1, 1));
        g1.setGhostPosition(new Position(5, 5));
        assertEquals(5, g1.getGhostX());
        assertEquals(5, g1.getGhostY());
        assertEquals(new Position(5, 5), g1.getGhostPosition());
    }
}
