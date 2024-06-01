package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// a class to test the Score class
public class TestScore {
    private Score s1;
    private JSONObject json = new JSONObject();

    @BeforeEach
    public void runBefore() {
        s1 = new Score(0);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, s1.getScore());
    }

    @Test
    public void testIncrementScore() {
        s1.incrementScore();
        assertEquals(1, s1.getScore());
    }

    @Test
    public void testIncrementScoreTwice() {
        s1.incrementScore();
        s1.incrementScore();
        assertEquals(2, s1.getScore());
    }
}
