package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

// Represents the panel in which the scoreboard is displayed.
// Note: code in this file has been referenced from B02-SpaceInvadersBase
public class ScorePanel extends JPanel {
    private static final String SCORE = "Score: ";
    private Game game;
    private JLabel score;
    private static final int SCORE_WIDTH = 200;
    private static final int SCORE_HEIGHT = 20;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Game g) {
        this.game = g;
        setBackground(new Color(145, 5, 238));
        score = new JLabel(SCORE + game.getPacmanCoinScore());
        score.setPreferredSize(new Dimension(SCORE_WIDTH, SCORE_HEIGHT));
        add(score);
        add(Box.createHorizontalStrut(10));
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        score.setText(SCORE + game.getPacmanCoinScore());
        repaint();
    }
}
