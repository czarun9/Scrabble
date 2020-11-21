package BackEnd.Scrabble;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {

    private List<Block> rack;
    private String playerName;
    private Game game;
    private String chosenTile;
    private int puttedLettersInTurn;
    private int firstX;
    private int secondX;
    private int firstY;
    private int secondY;
    private String word;
    private int score=0;

    public Player(String name, Game game) {
        this.rack = new ArrayList<>(7);
        this.playerName = name;
        this.game = game;
        this.chosenTile = null;

        fillRack();
    }

    public void fillRack() {
        for (int i = 0; i < 7; i++) {
            rack.add(game.getScrabbleSet().drawBlock());
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Block> getRack() {
        return rack;
    }

    public void setChosenTile(String chosenTile) {
        this.chosenTile = chosenTile;
    }

    public String getChosenTile() {
        return chosenTile;
    }

    public int getPuttedLettersInTurn() {
        return puttedLettersInTurn;
    }

    public void setPuttedLettersInTurn(int puttedLettersInTurn) {
        this.puttedLettersInTurn = puttedLettersInTurn;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFirstY() {
        return firstY;
    }

    public void setFirstX(int x) {
        this.firstX = x;
    }

    public void setFirstY(int y) {
        this.firstY = y;
    }

    public int getSecondX() {
        return secondX;
    }

    public void setSecondX(int secondX) {
        this.secondX = secondX;
    }

    public int getSecondY() {
        return secondY;
    }

    public void setSecondY(int secondY) {
        this.secondY = secondY;
    }

    public void setWord(String word) {
        this.word = word;

    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void run() {

        while (game.getIsGame() == true) {
            try {
                game.getPlayersSemaphore().acquire();

                firstX = -1;
                firstY = -1;
                secondX = -1;
                secondY = -1;

                System.out.println(game.getPlayer2Turn());

                System.out.println(this);
                System.out.println(game.getPlayer2Turn());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
