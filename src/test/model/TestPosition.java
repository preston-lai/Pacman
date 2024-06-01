package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// a class to test the Position class
public class TestPosition {
    Position pos1;
    Position pos2;
    Pacman p1 = new Pacman(1, 1);
    Pacman p2 = new Pacman(2,2);


    @BeforeEach
    public void runBefore() {
        pos1 = new Position(1, 1);
        pos2 = new Position(3, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, pos1.getX());
        assertEquals(1, pos1.getY());
    }

    @Test
    public void testEquals() {
        assertTrue(pos1.equals(pos1));
        assertFalse(pos1.equals(pos2));
        assertFalse(pos2.equals(null));
        p1.setPacmanPosition(new Position(20, 20));
        assertFalse(p1.getPacmanPosition().equals(p2.getPacmanPosition()));
        assertFalse(p2.getPacmanPosition().equals(p1.getPacmanPosition()));
        assertFalse(pos1.equals(null));
    }

    @Test
    public void testEqualsMultiple() {
        assertTrue(pos1.equals(pos1));
        assertTrue(pos1.equals(pos1));
        assertFalse(pos1.equals(p1));
        assertFalse(p2.equals(p1));
        assertEquals(pos1.equals(null), pos1.equals(p1));
        // how to test a null object (or create a null object)
        pos1 = new Position(20, 10);
        pos2 = (new Position(20, 20));
        assertEquals(pos1.getX(), pos2.getX());
        assertEquals(pos1.equals(pos2), pos1.getY() == pos2.getY());
    }
}
