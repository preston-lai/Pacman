package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

// main creates and starts the game. Also, produces an exception if file not found.
// code in this file has been referenced from B02-SpaceInvadersBase
// code in this file has been referenced from JSONDemo
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            new TerminalGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
