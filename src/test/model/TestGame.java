package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// a class to test the Game class
class TestGame {
    Game testGame;
    Ghost g1;
    Ghost g2;
    List<Ghost> ghosts;
    Coin c1;
    Coin c2;
    List<Coin> coins;
    Pacman p1;
    Pacman pacman;
    Score s1;
    Score s2;
    TopScores topScores;
    Event e;

    @BeforeEach
    public void runBefore() {
        testGame = new Game();
        g1 = new Ghost(1,1);
        g2 = new Ghost(2, 2);
        ghosts = testGame.getGhosts();
        c1 = new Coin(1,1);
        c2 = new Coin(2, 2);
        coins = testGame.getCoins();
        p1 = new Pacman(1, 1);
        pacman = testGame.getPacman();
        s1 = new Score(1);
        s2 = new Score(2);
        topScores = testGame.getTopScores();
        e = new Event("Score increased by 1");   // (1)
    }

    @Test
    public void testGetHighestScoreThree() {
        topScores.addScore(new Score(0));
        topScores.addScore(new Score(10));
        topScores.addScore(new Score(9));
        topScores.addScore(new Score(20));
        topScores.addScore(new Score(100));
        assertEquals(100, testGame.getHighestScore());
        assertEquals("Highest Score: 100", testGame.convertHighestScoreToString());
    }

    @Test
    public void testGetHighestScoreTwice() {
        topScores.addScore(s1);
        topScores.addScore(s2);
        s2.incrementScore();
        assertEquals(3, testGame.getHighestScore());
        assertEquals("Highest Score: 3", testGame.convertHighestScoreToString());
    }


    @Test
    public void testGetHighestScoreOnce() {
        topScores.addScore(s1);
        assertEquals(1, testGame.getHighestScore());
        assertEquals("Highest Score: 1", testGame.convertHighestScoreToString());
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testGame.getPacmanCoinScore());
        assertEquals(0, testGame.getCoins().size());
        assertEquals(0, testGame.getGhosts().size());
        assertEquals(new Position(1, 1), pacman.getPacmanPosition());
    }

    @Test
    public void testUpdatePacmanHitCoin() {
        testGame.update();
        pacman.movePacman();
        pacman.setPacmanPosition(new Position(2,2));
        coins.add(c1);
        c1.setCoinPosition(new Position(2, 2));
        assertEquals(pacman.getPacmanX(), c1.getCoinX());
        assertEquals(pacman.getPacmanY(), c1.getCoinY());
        assertTrue(testGame.checkPacmanHitCoin());
        testGame.addCoinToScore();
        assertEquals(1, testGame.getGameScore().getScore());
    }

    @Test
    public void testUpdatePacmanNotHitCoin() {
        testGame.update();
        pacman.setPacmanPosition(new Position(4,4));
        c1.setCoinPosition(new Position(2, 2));
        c2.setCoinPosition(new Position(60, 50));
        coins.add(c1);
        coins.add(c2);
        assertFalse(testGame.checkPacmanHitCoin());
    }

    @Test
    public void testUpdatePacmanNotHitCoin2() {
        pacman.setPacmanPosition(new Position(2,50));
        c1.setCoinPosition(new Position(2, 6));
        c2.setCoinPosition(new Position(2, 50));
        coins.add(c1);
        coins.add(c2);
        assertEquals(pacman.getPacmanX(), c1.getCoinX());
        assertEquals(pacman.getPacmanY(), c2.getCoinY());
        assertTrue(testGame.checkPacmanHitCoin());
    }

    @Test
    public void testUpdatePacmanNotHitCoin3() {
        pacman.setPacmanPosition(new Position(60,50));
        c1.setCoinPosition(new Position(60, 6));
        c2.setCoinPosition(new Position(2, 50));
        coins.add(c1);
        coins.add(c2);
        assertEquals(pacman.getPacmanX(), c1.getCoinX());
        assertEquals(pacman.getPacmanY(), c2.getCoinY());
        assertFalse(testGame.checkPacmanHitCoin());
    }

    @Test
    public void testUpdateGameOver() {
        pacman.movePacman();
        pacman.setPacmanPosition(new Position(2,2));
        coins.add(c1);
        c1.setCoinPosition(new Position(2, 2));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(2, 2));
        assertEquals(pacman.getPacmanX(), c1.getCoinX());
        assertEquals(pacman.getPacmanY(), c1.getCoinY());
        s1.incrementScore();
        assertEquals(2, s1.getScore());
        assertEquals(pacman.getPacmanX(), g1.getGhostX());
        assertEquals(pacman.getPacmanY(), g1.getGhostY());
        assertTrue(testGame.checkGameOver());
        testGame.gameOverSaveToJson();
    }

    @Test
    public void testKeyPressedR() {
        pacman.setPacmanPosition(new Position(5, 5));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(5, 5));
        assertTrue(testGame.checkGameOver());
        testGame.setUp();
    }

    @Test
    public void testKeyPressedNone() {
        pacman.setPacmanPosition(new Position(2,2));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(2, 2));
        pacman.setPacmanPosition(new Position(2, 2));
        assertTrue(testGame.checkGameOver());
    }

    @Test
    public void testMovePacmanLeft() {
        pacman.setPacmanDirection(Direction.LEFT);
        pacman.faceLeft();
        assertEquals(Direction.LEFT, pacman.getPacmanDirection());
    }

    @Test
    public void testMovePacmanRight() {
        pacman.setPacmanDirection(Direction.RIGHT);
        pacman.faceRight();
        assertEquals(Direction.RIGHT, pacman.getPacmanDirection());
    }

    @Test
    public void testMovePacmanDown() {
        pacman.setPacmanDirection(Direction.DOWN);
        pacman.faceDown();
        assertEquals(Direction.DOWN, pacman.getPacmanDirection());
    }

    @Test
    public void testMovePacmanUP() {
        pacman.setPacmanDirection(Direction.UP);
        pacman.faceUp();
        assertEquals(Direction.UP, pacman.getPacmanDirection());
    }

    @Test
    public void testIsOver() {
        pacman.setPacmanPosition(new Position(2,2));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(2, 2));
        assertTrue(testGame.checkGameOver());
        assertTrue(testGame.isOver());
    }

    @Test
    public void testPrintScoresOnce() {
        s1.incrementScore();
        assertEquals(2, s1.getScore());
        topScores.addScore(s1);
        assertEquals("Top 5 Scores: 2", testGame.printScores());
    }

    @Test
    public void testPrintScoresTwice() {
        s1.incrementScore();
        s1.incrementScore();
        assertEquals(3, s1.getScore());
        s2.incrementScore();
        s2.incrementScore();
        assertEquals(4, s2.getScore());
        topScores.addScore(s1);
        topScores.addScore(s2);
        assertEquals("Top 5 Scores: 3, 4", testGame.printScores());
    }

    @Test
    public void testLoadScoresOnce() {
//        assertEquals("Loaded [model.Score@7fd50002, model.Score@533bda92] from ./data/topScores.json",
//                testGame.loadScores());
        testGame.loadScores();
    }

    @Test
    public void testLoadScoresTwice() {
        s1.incrementScore();
        s1.incrementScore();
        assertEquals(3, s1.getScore());
        s2.incrementScore();
        assertEquals(3, s2.getScore());
        topScores.addScore(s1);
        topScores.addScore(s2);
        assertEquals("Loaded [] from ./data/topScores.json",
                testGame.loadScores());
        testGame.loadScores();
    }

    @Test
    public void testSaveScores() {
        testGame.saveScores();
    }

    @Test
    public void testAddGhostOne() {
        testGame.addGhost(g1);
        List<Ghost> actual = testGame.getGhosts();
        List<Ghost> expected = new ArrayList<>();
        expected.add(g1);
        assertEquals(actual, expected);
        assertEquals(actual.size(), expected.size());
    }

    @Test
    public void testAddGhostTwo() {
        testGame.addGhost(g1);
        testGame.addGhost(g2);
        List<Ghost> actual = testGame.getGhosts();
        List<Ghost> expected = new ArrayList<>();
        expected.add(g1);
        expected.add(g2);
        assertEquals(actual, expected);
        assertEquals(actual.size(), expected.size());
    }

    @Test
    public void testAddCoinOne() {
        testGame.addCoin(c1);
        List<Coin> actual = testGame.getCoins();
        List<Coin> expected = new ArrayList<>();
        expected.add(c1);
        assertEquals(actual, expected);
        assertEquals(actual.size(), expected.size());
    }

    @Test
    public void testAddCoinTwo() {
        testGame.addCoin(c1);
        testGame.addCoin(c1);
        List<Coin> actual = testGame.getCoins();
        List<Coin> expected = new ArrayList<>();
        expected.add(c1);
        expected.add(c1);
        assertEquals(actual, expected);
        assertEquals(actual.size(), expected.size());
    }


    @Test
    public void testGetPacmanCoinScoreZero() {
        assertEquals(0, testGame.getPacmanCoinScore());
    }

    @Test
    public void testGetPacmanCoinScoreOne() {
        testGame.addCoinToScore();
        assertEquals(1, testGame.getPacmanCoinScore());
    }

    @Test
    public void testGetPacmanCoinScoreTwo() {
        testGame.addCoinToScore();
        testGame.addCoinToScore();
        assertEquals(2, testGame.getPacmanCoinScore());
    }

    @Test
    public void testGameOver() {
        Pacman testP = new Pacman(10, 10);
        testP.setPacmanPosition(new Position(1, 1));
        assertTrue(testP.getPacmanPosition().equals(g1.getGhostPosition()));
    }

    @Test
    public void testNotGameOver() {
        Pacman testP = new Pacman(10, 10);
        testP.setPacmanPosition(new Position(1, 1));
        assertFalse(testGame.checkGameOver());
        assertTrue(testP.getPacmanPosition().equals(g1.getGhostPosition()));
    }

    @Test
    public void testGameOverAgain() {
        pacman.setPacmanPosition(new Position(20, 10));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(20, 10));
        assertTrue(testGame.checkGameOver());
        assertTrue(pacman.getPacmanPosition().equals(g1.getGhostPosition()));
    }

    @Test
    public void testGameOverThree() {
        pacman.setPacmanPosition(new Position(20, 10));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(20, 10));
        assertTrue(testGame.checkGameOver());
    }

    @Test
    public void testGameOverFour() {
        pacman.setPacmanPosition(new Position(20, 5));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(20, 10));
        assertFalse(testGame.checkGameOver());
    }

    @Test
    public void testGameOverFifth() {
        pacman.setPacmanPosition(new Position(10, 10));
        ghosts.add(g1);
        g1.setGhostPosition(new Position(20, 10));
        assertFalse(testGame.checkGameOver());
    }

    @Test
    public void testUpdate() {
        testGame.update();
        assertEquals(2, pacman.getPacmanX());
        assertEquals(1, pacman.getPacmanY());
        assertFalse(testGame.checkGameOver());
        p1.setPacmanPosition(new Position(10, 10));
        assertFalse(p1.getPacmanPosition().equals(g1.getGhostPosition()));
    }

    @Test
    public void testUpdateEventLog() {
        testGame.update();
        assertEquals(2, pacman.getPacmanX());
        assertEquals(1, pacman.getPacmanY());
        assertFalse(testGame.checkGameOver());
        p1.setPacmanPosition(new Position(10, 10));
        assertFalse(p1.getPacmanPosition().equals(g1.getGhostPosition()));
        assertEquals("Score increased by 1", e.getDescription());
    }

    @Test
    public void testTickTwice() {
        testGame.update();
        testGame.update();
        p1.setPacmanPosition(new Position(10, 10));
        assertFalse(p1.getPacmanPosition().equals(g1.getGhostPosition()));
        p1.movePacman();
        assertFalse(testGame.checkGameOver());
        p1.setPacmanPosition(new Position(20, 20));
        assertFalse(p1.getPacmanPosition().equals(g1.getGhostPosition()));
    }

    @Test
    public void testGameMakingMoneyOnce() {
        c1.setCoinPosition(new Position(1, 1));
        assertTrue(p1.getPacmanPosition().equals(c1.getCoinPosition()));
    }

    @Test
    public void testGameMakingMoneyTwice() {
        c1.setCoinPosition(new Position(1, 1));
        c2.setCoinPosition(new Position(2, 2));
        assertFalse(testGame.checkPacmanHitCoin());
    }

    @Test
    public void testGameMakingMoneyThird() {
        c1.setCoinPosition(new Position(1, 1));
        assertFalse(testGame.checkPacmanHitCoin());
    }

    @Test
    public void testGameMakingMoneyFourth() {
        c1.setCoinPosition(new Position(1, 8));
        c2.setCoinPosition(new Position(7, 1));
        coins.add(c1);
        coins.add(c2);
        c1.setCoinPosition(pacman.getPacmanPosition());
        assertTrue(testGame.checkPacmanHitCoin());
    }
}