package persistence;

import model.Score;
import model.TopScores;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class for JSON reading
// Referenced JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TopScores ts = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTopScores() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTopScores.json");
        try {
            TopScores ts = reader.read();
            //assertEquals("My List of Top Scores", ts.getTopFiveScores());
            assertEquals(0, ts.getNumScores());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderTopScores() {
        JsonReader reader = new JsonReader("./data/testReaderTopScores.json");
        try {
            TopScores ts = reader.read();
            List<Score> expected = ts.getTopFiveScores();
            assertEquals(3, expected.size());
            assertEquals(2, expected.get(0).getScore());
            assertEquals(15, expected.get(1).getScore());
            assertEquals(27, expected.get(2).getScore());
            //checkScore(10, expected.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
