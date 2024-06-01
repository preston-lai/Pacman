package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// a class representing a list of scores after running multiple games of Pacman
public class TopScores implements Writable {
    private List<Score> topFiveScores;
    //private List<Integer> topFiveScoresAsInteger;

    public TopScores() {
        this.topFiveScores = new ArrayList<>();
        //this.topFiveScoresAsInteger = new ArrayList<>();
    }

    public List<Score> getTopFiveScores() {
        return this.topFiveScores;
    }

    public int getNumScores() {
        return this.topFiveScores.size();
    }

    public void addScore(Score score) {
        this.topFiveScores.add(score);
    }

    // EFFECTS: returns list of scores in the TopScores as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Score s : topFiveScores) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: inputs the scores into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        // json.put("name", name);
        json.put("scores", thingiesToJson());
        return json;
    }
}
