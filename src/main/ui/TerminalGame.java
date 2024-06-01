package ui;

import model.Event;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// TerminalGame setsup the window in which Pacman will be played
// Note: code in this file has been referenced from JSONDemo
// Note: code in this file has been referenced from B02-SpaceInvadersBase
// https://stackoverflow.com/questions/25627023/trying-to-add-actionlistener-to-jbuttons
// https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
public class TerminalGame extends JFrame {
    private static final int INTERVAL = 2;
    private GamePanel gp;
    private ScorePanel sp;
    private static Game game = new Game();
    private Timer gameTimer;
    private Scanner input;

    // Constructs main window
    // EFFECT: sets up window in which Pacman game will be played
    public TerminalGame() throws IOException, InterruptedException {
        super("Homepage");
        JFrame frame = new JFrame("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        input = new Scanner(System.in);
        renderHomeMenu();

        setSize(600, 600);
        //setUndecorated(false);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
    }

    public static void printLog(EventLog events) {
        for (Event e : events) {
            System.out.println(e);
        }
    }

    // EFFECTS: opens the game window
    public void openGameWindow() {
        timer();
        JFrame newFrame = new JFrame("Pacman Game");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(400, 300);
        newFrame.setVisible(true);
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        newFrame.add(gp);
        newFrame.add(sp, BorderLayout.NORTH);
        newFrame.addKeyListener(new KeyHandler());
        newFrame.pack();
        centreOnScreen();
    }

    // Set up timer
    // MODIFIES: none
    // EFFECT:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    public void timer() {
        game.setUp();
        gameTimer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                game.update();
                if (game.isOver()) {
                    gameTimer.stop();
                }
                gp.repaint();
                sp.update();
            }
        });
        gameTimer.start();
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // EFFECTS: A key handler to respond to key events
    private class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            TerminalGame.this.keyPressed(e.getKeyCode());
        }
    }

    //     MODIFIES: this
    //     effects:  moves Pacman and resets game in response to given key pressed
    //               given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_R || game.checkGameOver()) {
            game.setUp();
        } else if (keyCode == KeyEvent.VK_Q) {
            System.exit(0);
        } else {
            movePacmanByKey(keyCode);
        }
    }

    //     EFFECTS: moves Pacman in response to given key pressed
    public void movePacmanByKey(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            game.getPacman().setPacmanDirection(Direction.LEFT);
            game.getPacman().faceLeft();
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            game.getPacman().setPacmanDirection(Direction.RIGHT);
            game.getPacman().faceRight();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            game.getPacman().setPacmanDirection(Direction.DOWN);
            game.getPacman().faceDown();
        }
        if (keyCode == KeyEvent.VK_UP) {
            game.getPacman().setPacmanDirection(Direction.UP);
            game.getPacman().faceUp();
        }
    }

    // EFFECT: creates a menu which displays buttons users can click from JSON saving to
    //         playing Pacman
    public void renderHomeMenu() {
        homeMenuHelper();
        JButton play = new JButton("play game");
        JButton print = new JButton("print top five scores");
        JButton highest = new JButton("print highest score");
        JButton save = new JButton("save scores to file");
        JButton load = new JButton("load scores from file");
        JButton quit = new JButton("quit");
        setLayout(new GridLayout(7, 1));

        add(play);
        add(print);
        add(highest);
        add(save);
        add(load);
        add(quit);

        extractedPlay(play);
        extractedPrint(print);
        extractedHigh(highest);
        extractedSave(save);
        extractedLoad(load);
        extractedQuit(quit);
        pack();
    }

    public void homeMenuHelper() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon image = addImage("data/pacman.png");
        JLabel imageLabel = new JLabel(image);
        add(imageLabel);
    }

    public ImageIcon addImage(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // EFFECT: extracted method from renderHomeMenu
    public void extractedHigh(JButton highest) {
        highest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TerminalGame.this, game.convertHighestScoreToString());
            }
        });
    }

    // EFFECT: extracted method from renderHomeMenu
    public static void extractedQuit(JButton quit) {
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    // EFFECT: extracted method from renderHomeMenu
    public void extractedLoad(JButton load) {
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventLog.getInstance().logEvent(new Event("Score Loaded"));
                JOptionPane.showMessageDialog(TerminalGame.this, game.loadScores());
            }
        });
    }

    // EFFECT: extracted method from renderHomeMenu
    public void extractedSave(JButton save) {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventLog.getInstance().logEvent(new Event("Score Saved"));
                JOptionPane.showMessageDialog(TerminalGame.this, game.saveScores());
            }
        });
    }

    // EFFECT: extracted method from renderHomeMenu
    public void extractedPrint(JButton print) {
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TerminalGame.this, game.printScores());
            }
        });
    }

    // EFFECT: extracted method from renderHomeMenu
    private void extractedPlay(JButton play) {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGameWindow();
            }
        });
    }
}
