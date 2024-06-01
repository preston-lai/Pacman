package persistence;

import model.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class for all JSON files
// Referenced JsonSerializationDemo
public class JsonTest {
    protected void checkScore(int i, Score score) {
        assertEquals(i, score.getScore());
    }
}
