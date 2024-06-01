package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// a class to test the Pacman class
class TestPacman {
    Pacman testPacman;

    @BeforeEach
    public void runBefore() {
        testPacman = new Pacman(1, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(new Position(1, 1), testPacman.getPacmanPosition());
        assertEquals(1, testPacman.getPacmanPosition().getX());
        assertEquals(1, testPacman.getPacmanPosition().getY());
        assertEquals(Direction.RIGHT, testPacman.getPacmanDirection());
    }

    @Test // TODO
    public void testHandleBoundaryX() {
        testPacman.setPacmanPosition(new Position(-1, 5));
        testPacman.handleBoundary();
        assertEquals(0, testPacman.getPacmanX());
        assertEquals(5, testPacman.getPacmanY());
    }

    @Test
    public void testHandleBoundaryX2() {
        testPacman.setPacmanPosition(new Position(820, 5));
        testPacman.handleBoundary();
        assertEquals(795, testPacman.getPacmanX());
        assertEquals(5, testPacman.getPacmanY());
    }

    @Test // TODO
    public void testHandleBoundaryY() {
        testPacman.setPacmanPosition(new Position(5, -1));
        testPacman.handleBoundary();
        assertEquals(5, testPacman.getPacmanX());
        assertEquals(0, testPacman.getPacmanY());
    }

    @Test
    public void testHandleBoundaryY2() {
        testPacman.setPacmanPosition(new Position(5, 620));
        testPacman.handleBoundary();
        assertEquals(5, testPacman.getPacmanX());
        assertEquals(595, testPacman.getPacmanY());
    }

    @Test
    public void testFaceRight() {
        testPacman.faceRight();
        assertEquals(Direction.RIGHT, testPacman.getPacmanDirection());
    }

    @Test
    public void testFaceLeft() {
        testPacman.faceLeft();
        assertEquals(Direction.LEFT, testPacman.getPacmanDirection());
    }

    @Test
    public void testFaceUP() {
        testPacman.faceUp();
        assertEquals(Direction.UP, testPacman.getPacmanDirection());
    }

    @Test
    public void testFaceDown() {
        testPacman.faceDown();
        assertEquals(Direction.DOWN, testPacman.getPacmanDirection());
    }

    @Test
    public void testSetPacmanDirectionUp() {
        testPacman.setPacmanDirection(Direction.UP);
        assertEquals(Direction.UP, testPacman.getPacmanDirection());
    }

    @Test
    public void testSetPacmanDirectionDown() {
        testPacman.setPacmanDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, testPacman.getPacmanDirection());
    }

    @Test
    public void testSetPacmanDirectionMultiple() {
        testPacman.setPacmanDirection(Direction.DOWN);
        testPacman.setPacmanDirection(Direction.UP);
        testPacman.setPacmanDirection(Direction.RIGHT);
        testPacman.setPacmanDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, testPacman.getPacmanDirection());
    }

    @Test
    public void testSetPacmanPositionOnce() {
        testPacman.setPacmanPosition(new Position(20, 20));
        assertEquals(new Position(20, 20), testPacman.getPacmanPosition());
        assertEquals(20, testPacman.getPacmanPosition().getX());
        assertEquals(20, testPacman.getPacmanPosition().getY());
    }

    @Test
    public void testSetPacmanPositionTwice() {
        testPacman.setPacmanPosition(new Position(20, 20));
        testPacman.setPacmanPosition(new Position(40, 40));
        assertEquals(new Position(40, 40), testPacman.getPacmanPosition());
        assertEquals(40, testPacman.getPacmanPosition().getX());
        assertEquals(40, testPacman.getPacmanPosition().getY());
        assertEquals(40, testPacman.getPacmanX());
        assertEquals(40, testPacman.getPacmanY());
    }

    @Test
    public void testMovePacmanOnce() {
        testPacman.movePacman();
        assertEquals(2, testPacman.getPacmanX());
        assertEquals(1, testPacman.getPacmanY());
        assertEquals(new Position(2, 1), testPacman.getPacmanPosition());
    }

    @Test
    public void testMovePacmanTwice() {
        testPacman.movePacman();
        testPacman.movePacman();
        assertEquals(3, testPacman.getPacmanX());
        assertEquals(1, testPacman.getPacmanY());
        assertEquals(new Position(3, 1), testPacman.getPacmanPosition());
    }
}