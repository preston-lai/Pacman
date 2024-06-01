package model;

import org.json.JSONObject;
import persistence.Writable;

// a class representing a score after running 1 game of Pacman
public class Score implements Writable {
    private int score;

    public Score(int score) {
        this.score = score;
    }


    // EFFECTS: increments the score by 1
    public void incrementScore() {
        this.score = this.score + 1;
    }

    public int getScore() {
        return this.score;
    }

    // EFFECTS: puts the JSON object to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scores", score);
        return json;
    }


}
