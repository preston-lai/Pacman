package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// a class to test the coin class
public class TestCoin {
    private Coin c1;

    @BeforeEach
    public void runBefore() {
        c1 = new Coin(5, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(new Position(5, 5), c1.getCoinPosition());
        assertEquals(5, c1.getCoinX());
        assertEquals(5, c1.getCoinY());
    }

    @Test
    public void testSetCoinPositionOnce() {
        c1.setCoinPosition(new Position(30, 30));
        assertEquals(new Position(30, 30), c1.getCoinPosition());
        assertEquals(30, c1.getCoinX());
        assertEquals(30, c1.getCoinY());
    }

    @Test
    public void testSetCoinPositionTwice() {
        c1.setCoinPosition(new Position(30, 30));
        c1.setCoinPosition(new Position(28, 4));
        assertEquals(new Position(28, 4), c1.getCoinPosition());
        assertEquals(28, c1.getCoinX());
        assertEquals(4, c1.getCoinY());
    }

}
