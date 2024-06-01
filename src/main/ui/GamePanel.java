package ui;

import model.Coin;
import model.Game;
import model.Ghost;
import model.Pacman;

import javax.swing.*;
import java.awt.*;

// the panel in which the game is rendered
// Note: code in this file has been referenced from B02-SpaceInvadersBase
public class GamePanel extends JPanel {
    private Game game;
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";

    // EFFECTS: sets the size and background colour of panel
    //          updates this with the game to be displayed
    public GamePanel(Game g) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
    }

    // EFFECTS: renders the game and checks if it's ever game over
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.renderGame(g);

        if (game.checkGameOver()) {
            game.gameOverSaveToJson();
            renderGameOver(g);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    public void renderGame(Graphics g) {
        this.renderPacman(g);
        this.renderCoins(g);
        this.renderGhosts(g);
    }

    // EFFECTS: renders game over text
    public void renderGameOver(Graphics g) {
        //System.exit(0);
        Color saved = g.getColor();
        g.setColor(new Color(238, 5, 5));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, Game.HEIGHT / 2);
        //centreString(REPLAY, g, fm, Game.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Game.WIDTH - width) / 2, y);
    }

    // EFFECTS: renders Pacman onto game
    public void renderPacman(Graphics g) {
        Pacman pacman = game.getPacman();
        g.setColor(new Color(241, 229, 9));
        //g.fillArc(pacman.getPacmanX(), pacman.getPacmanY(), 20, 20, 45, 270);
        g.fillOval(pacman.getPacmanX(), pacman.getPacmanY(), 20, 20);
    }

    // MODIFIES: g
    // EFFECTS: renders all coins onto game
    public void renderCoins(Graphics g) {
        game.addCoin(new Coin(25, 25));
        game.addCoin(new Coin(400, 500));
        game.addCoin(new Coin(40, 300));
        game.addCoin(new Coin(90, 200));
        game.addCoin(new Coin(600, 600));
        game.addCoin(new Coin(50, 30));
        game.addCoin(new Coin(200, 100));
        for (Coin next : game.getCoins()) {
            this.renderCoin(g, next);
        }
    }

    // EFFECTS:  renders a coin onto the game
    public void renderCoin(Graphics g, Coin coin) {
        g.setColor(Color.GREEN);
        g.fillOval(coin.getCoinX(), coin.getCoinY(), 20, 20);
    }

    // MODIFIES: g
    // EFFECTS: renders ghosts onto game
    public void renderGhosts(Graphics g) {
        game.addGhost(new Ghost(100, 50));
        game.addGhost(new Ghost(300, 400));
        for (Ghost next : game.getGhosts()) {
            this.renderGhost(g, next);
        }
    }

    // EFFECTS:  renders a single ghost onto the game
    public void renderGhost(Graphics g, Ghost ghost) {
        g.setColor(Color.BLUE);
        g.fillOval(ghost.getGhostX(), ghost.getGhostY(), 20, 20);
    }
}
