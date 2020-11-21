package BackEnd.Scrabble;

import BackEnd.Scrabble.GUI.west.BoardSpace;

import java.util.concurrent.Semaphore;

public class Game extends Thread {

    private ScrabbleSet scrabbleSet;
    private Player[] players;
    private boolean isGame;
    private Semaphore playersSemaphore;
    private int isPlayer2Turn;


    public Game(String nameP1, String nameP2) {
        this.scrabbleSet = new ScrabbleSet();
        this.players = new Player[2];
        this.isGame = true;
        this.scrabbleSet = new ScrabbleSet();
        this.playersSemaphore = new Semaphore(1, true);


        players[0] = new Player(nameP1, this);
        players[1] = new Player(nameP2, this);
    }


    public void run() {
        isPlayer2Turn = 0;
        players[0].start();
        players[0].setName("Thread-Player1");
        players[1].start();
        players[1].setName("Thread-Player2");

        while (isGame == true) {


        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public ScrabbleSet getScrabbleSet() {
        return scrabbleSet;
    }

    public boolean getIsGame() {
        return isGame;
    }

    public Semaphore getPlayersSemaphore() {
        return playersSemaphore;
    }

    public int getPlayer2Turn() {
        return isPlayer2Turn;
    }

    public void setPlayer2Turn(int player2Turn) {
        isPlayer2Turn = player2Turn;
    }


}
