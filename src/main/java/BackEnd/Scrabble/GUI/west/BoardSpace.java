package BackEnd.Scrabble.GUI.west;

import BackEnd.Scrabble.Block;
import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.Game;
import com.rits.cloning.Cloner;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class BoardSpace extends JPanel implements MouseListener {

    private Color tempColor;
    private Color color;
    private boolean canBeLaid;
    private Game game;
    private JLabel letter;
    private JLabel points;
    private MainPanel mainPanel;
    private int x;
    private int y;
    private int letterMultiplier = 1;
    private int wordMultiplier = 1;

    public BoardSpace(Game game, MainPanel mainPanel, int x, int y) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.canBeLaid = false;
        this.mainPanel = mainPanel;
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(new Color(6, 131, 68));
        lettersConfig();
        addMouseListener(this);
    }



    public void lettersConfig() {
        this.letter = new JLabel();
        this.points = new JLabel();
        letter.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        points.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        letter.setAlignmentX(Component.CENTER_ALIGNMENT);
        letter.setAlignmentY(Component.CENTER_ALIGNMENT);
        points.setAlignmentX(Component.CENTER_ALIGNMENT);
        points.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(letter);
        add(points);
    }

    public void setCanBeLaid(boolean canBeLaid) {
        this.canBeLaid = canBeLaid;
    }

    public JLabel getLetter() {
        return letter;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (canBeLaid == true && game.getPlayers()[game.getPlayer2Turn()].getPuttedLettersInTurn() == 0) {

            if (game.getPlayers()[game.getPlayer2Turn()].getChosenTile() != null) {
                BoardSpace tempBoardSpace = mainPanel.getWestPanel().getBoard()[x][y];
                mainPanel.getWestPanel().setSpacesForChange(tempBoardSpace);
                String chosenTile = game.getPlayers()[game.getPlayer2Turn()].getChosenTile();
                letter.setText(chosenTile);
                points.setText(String.valueOf(game.getScrabbleSet().getPoints(chosenTile)));

                mainPanel.getBottomPanel().getWestBottomPanel().refreshRack();


                setColor(new Color(238, 224, 154));
                game.getPlayers()[game.getPlayer2Turn()].setPuttedLettersInTurn(1);


                mainPanel.getWestPanel().setActiveBoardSpaces();
                game.getPlayers()[game.getPlayer2Turn()].setFirstX(this.x);
                game.getPlayers()[game.getPlayer2Turn()].setFirstY(this.y);
                game.getPlayers()[game.getPlayer2Turn()].setChosenTile(null);

            }

        } else if (canBeLaid == true && game.getPlayers()[game.getPlayer2Turn()].getPuttedLettersInTurn() > 0) {
            if (game.getPlayers()[game.getPlayer2Turn()].getChosenTile() != null) {
                BoardSpace tempBoardSpace = mainPanel.getWestPanel().getBoard()[x][y];
                mainPanel.getWestPanel().setSpacesForChange(tempBoardSpace);
                String chosenTile = game.getPlayers()[game.getPlayer2Turn()].getChosenTile();
                letter.setText(chosenTile);
                points.setText(String.valueOf(game.getScrabbleSet().getPoints(chosenTile)));


                setColor(new Color(238, 224, 154));


                if (game.getPlayers()[game.getPlayer2Turn()].getPuttedLettersInTurn() == 1) {
                    game.getPlayers()[game.getPlayer2Turn()].setSecondX(this.x);
                    game.getPlayers()[game.getPlayer2Turn()].setSecondY(this.y);
                } else {
                    game.getPlayers()[game.getPlayer2Turn()].setFirstX(game.getPlayers()[game.getPlayer2Turn()].getSecondX());
                    game.getPlayers()[game.getPlayer2Turn()].setFirstY(game.getPlayers()[game.getPlayer2Turn()].getSecondY());
                    game.getPlayers()[game.getPlayer2Turn()].setSecondX(this.x);
                    game.getPlayers()[game.getPlayer2Turn()].setSecondY(this.y);
                }

                mainPanel.getWestPanel().setActiveBoardSpaces2(
                        game.getPlayers()[game.getPlayer2Turn()].getFirstX(),
                        game.getPlayers()[game.getPlayer2Turn()].getFirstY(),
                        game.getPlayers()[game.getPlayer2Turn()].getSecondX(),
                        game.getPlayers()[game.getPlayer2Turn()].getSecondY());
                game.getPlayers()[game.getPlayer2Turn()].setPuttedLettersInTurn(game.getPlayers()[game.getPlayer2Turn()].getPuttedLettersInTurn() + 1);
                game.getPlayers()[game.getPlayer2Turn()].setChosenTile(null);

            }

        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        tempColor = getBackground();
        setBackground(new Color(131, 46, 125));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (letter.getText() == "") {
            setBackground(tempColor);
        } else {
            setBackground(new Color(238, 224, 154));
        }
    }

    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    public void setLetterMultiplier(int letterMultiplier) {
        this.letterMultiplier = letterMultiplier;
    }

    public int getWordMultiplier() {
        return wordMultiplier;
    }

    public void setWordMultiplier(int wordMultiplier) {
        this.wordMultiplier = wordMultiplier;
    }

    public JLabel getPoints() {
        return points;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public int getThisX() {
        return this.x;
    }

    public int getThisY() {
        return this.y;
    }

}
