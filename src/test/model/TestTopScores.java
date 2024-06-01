package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// a class to test the TopScores class
public class TestTopScores {
    private TopScores ts;

    @BeforeEach
    public void runBefore() {
        ts = new TopScores();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, ts.getNumScores());
    }

    @Test
    public void testGetTopFiveScoresEmpty() {
        assertEquals(0, ts.getNumScores());
    }

    @Test
    public void testGetTopFiveScoresOnce() {
        ts.addScore(new Score(5));
        assertEquals(1, ts.getNumScores());
    }

    @Test
    public void testGetTopFiveScoresMultiple() {
        ts.addScore(new Score(5));
        ts.addScore(new Score(10));
        ts.addScore(new Score(20));
        ts.addScore(new Score(24));
        ts.addScore(new Score(25));
        assertEquals(5, ts.getNumScores());
    }
}
