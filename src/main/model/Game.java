package model;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Game class represents overall Pacman Game
// Note: code in this file has been referenced from B02-SpaceInvadersBase
public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private Pacman pacman;
    private List<Ghost> ghosts;
    private List<Coin> coins;
    private Score pacmanCoinScore = new Score(0);
    private TopScores topScores = new TopScores();
    private boolean isGameOver;
    private static final String JSON_STORE = "./data/topScores.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Game() {
        this.ghosts = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.isGameOver = false;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUp();
    }

    // MODIFIES: this
    // EFFECTS: updates Pacman and Ghost on clock tick
    public void update() {
        //moveGhost();
        pacman.movePacman();

        if (checkPacmanHitCoin()) {
            addCoinToScore();
            EventLog.getInstance().logEvent(new Event("Score increased by 1."));
        }
        if (checkGameOver()) {
            //gameOverSaveToJson();
        }
    }

    // EFFECTS: when game over saves to JSON
    public void gameOverSaveToJson() {
        Score finalScore = new Score(pacmanCoinScore.getScore());
        topScores.addScore(finalScore);
        EventLog.getInstance().logEvent(new Event("New Top Score Added: " + finalScore.getScore()));
    }

    // MODIFIES: this
    // EFFECTS: clears list of ghosts and coins, initializes Pacman
    public void setUp() {
        coins.clear();
        ghosts.clear();
        this.pacman = new Pacman(1, 1);
        isGameOver = false;
        this.pacmanCoinScore = new Score(0);
    }

    // EFFECTS: returns true if the game is over, otherwise false
    public boolean isOver() {
        return isGameOver;
    }

    // EFFECTS: returns true if Pacman hit a coin, otherwise false
    public boolean checkPacmanHitCoin() {
        for (Coin c : coins) {
            if (pacman.getPacmanX() == c.getCoinX() && pacman.getPacmanY() == c.getCoinY()) {
                return true;
            }
        }
        return false;
    }

    //     REQUIRES: nothing
    //     MODIFIES: nothing
    //     EFFECTS: returns false if Pacman hits a ghost
    public boolean checkGameOver() {
        for (Ghost g : ghosts) {
            if (pacman.getPacmanX() == g.getGhostX() && pacman.getPacmanY() == g.getGhostY()) {
                this.isGameOver = true;
                return true;
            }
        }
        return false;
    }

    //     REQUIRES: nothing
    //     MODIFIES: nothing
    //     EFFECTS: returns the highest score out of Top Scores
    public int getHighestScore() {
        int max = 0;

        for (Score s : topScores.getTopFiveScores()) {
            if (s.getScore() >= max) {
                max = s.getScore();
            }
        }
        return max;
    }

    public String convertHighestScoreToString() {
        return "Highest Score: " + getHighestScore();
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // prints all the scores in TopScores to the terminal console
    public String printScores() {
        StringBuilder result = new StringBuilder();

        for (Score s : topScores.getTopFiveScores()) {
            result.append(s.getScore()).append(", ");
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }

        String finalResult = result.toString();

        return "Top 5 Scores: " + finalResult;
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: saves the scores to file
    public String saveScores() {
        String result;
        try {
            jsonWriter.open();
            jsonWriter.write(topScores);
            jsonWriter.close();
            result = "Saved " + topScores.getTopFiveScores() + " to " + JSON_STORE;
            return result;
            //System.out.println("Saved " + topScores.getTopFiveScores() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //System.out.println("Unable to write to file: " + JSON_STORE);
            return "Unable to write to file: " + JSON_STORE;
        }
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: loads scores from file
    public String loadScores() {
        String result;
        try {
            topScores = jsonReader.read();
            result = "Loaded " + topScores.getTopFiveScores() + " from " + JSON_STORE;
            //System.out.println("Loaded " + topScores.getTopFiveScores() + " from " + JSON_STORE);
            return result;
        } catch (IOException e) {
            return "Unable to read from file: " + JSON_STORE;
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: adds a ghost to a list of Ghost
    public void addGhost(Ghost g) {
        this.ghosts.add(g);
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns a list of all the ghosts in the game
    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: returns a list of all the coins in the game
    public void addCoin(Coin c) {
        this.coins.add(c);
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: returns a list of all the ghosts in the game
    public List<Coin> getCoins() {
        return this.coins;
    }

    // REQUIRES: coinCount > 0
    // MODIFIES: this
    // EFFECTS: increments the number of coins in the game by 1
    public void addCoinToScore() {
        this.pacmanCoinScore.incrementScore();
    }

    public Score getGameScore() {
        return this.pacmanCoinScore;
    }

    public Pacman getPacman() {
        return this.pacman;
    }

    public int getPacmanCoinScore() {
        return this.pacmanCoinScore.getScore();
    }

    public TopScores getTopScores() {
        return this.topScores;
    }
}
