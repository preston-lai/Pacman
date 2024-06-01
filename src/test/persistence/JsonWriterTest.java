package persistence;

import model.Score;
import model.TopScores;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class for JSONWriter
// Referenced JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TopScores ts = new TopScores();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            TopScores ts = new TopScores();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTopScores.json");
            writer.open();
            writer.write(ts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTopScores.json");
            ts = reader.read();
            // assertEquals("My work room", ts.getTopFiveScores());
            assertEquals(0, ts.getNumScores());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TopScores ts = new TopScores();
            ts.addScore(new Score(10));
            ts.addScore(new Score(20));
            JsonWriter writer = new JsonWriter("./data/testWriterTopScores.json");
            writer.open();
            writer.write(ts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTopScores.json");
            ts = reader.read();
            assertEquals(2, ts.getNumScores());
            List<Score> expected = ts.getTopFiveScores();
            assertEquals(2, expected.size());
            assertEquals(10, expected.get(0).getScore());
            assertEquals(20, expected.get(1).getScore());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
