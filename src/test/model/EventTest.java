package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the Event class
 */
// Note: code in this file has been referenced from AlarmSystem
public class EventTest {
    private Event e;
    private Date d;
    private Event e2;
    private Event e3;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Score increased by 1");   // (1)
        e2 = new Event("test Event");
        e3 = null;
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Score increased by 1", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Score increased by 1", e.toString());
    }

    @Test
    public void testHashCode() {
        int result = e.hashCode();
        assertEquals(result, e.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(e.equals(e));
        assertFalse(e.equals(e2));
        assertFalse(e.equals(e3));
    }

    @Test
    public void testEqualsMultiple() {
        assertFalse(e.equals(e2));
        assertFalse(e.equals(e2));
        assertFalse(e.equals(d));
        assertFalse(d.equals(e));
    }
}
