package BackEnd.Scrabble;

public class Block {

    private String letter;
    private int points;

    public Block(String letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    public String getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }
}
