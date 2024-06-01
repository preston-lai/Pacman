package persistence;

import model.Score;
import model.TopScores;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Referenced JsonSerializationDemo
// Represents a reader that reads game scores from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TopScores read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTopScores(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses score from JSON object and returns it
    private TopScores parseTopScores(JSONObject jsonObject) {
        // String name = jsonObject.getString("name");
        TopScores ts = new TopScores();
        addScores(ts, jsonObject);
        return ts;
    }

    // MODIFIES: ts
    // EFFECTS: parses a score from JSON object and adds them to TopScores
    private void addScores(TopScores ts, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(ts, nextScore);
        }
    }

    // MODIFIES: score
    // EFFECTS: parses score from JSON object and adds it to TopScores
    private void addScore(TopScores ts, JSONObject jsonObject) {
        // String name = jsonObject.getString("name");
        int s = jsonObject.getInt("scores");
        Score score = new Score(s);
        ts.addScore(score);
    }
}

