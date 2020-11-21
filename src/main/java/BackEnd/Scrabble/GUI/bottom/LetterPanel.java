package BackEnd.Scrabble.GUI.bottom;

import BackEnd.Scrabble.Block;
import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class LetterPanel extends JPanel implements MouseListener {


    private Block block;
    private JLabel letter;
    private JLabel points;
    private Game game;
    private MainPanel mainPanel;


    public LetterPanel(Block block, Game game, MainPanel mainPanel) {
        this.block = block;
        this.game = game;
        this.mainPanel = mainPanel;

        setBackground(new Color(238, 224, 154));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        lettersConfig();
        addMouseListener(this);
    }


    public void lettersConfig() {
        this.letter = new JLabel(block.getLetter());
        this.points = new JLabel(String.valueOf(block.getPoints()));
        letter.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 40));
        points.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        letter.setAlignmentX(Component.CENTER_ALIGNMENT);
        letter.setAlignmentY(Component.CENTER_ALIGNMENT);
        points.setAlignmentX(Component.CENTER_ALIGNMENT);
        points.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(letter);
        add(points);


    }


    public void setLetter(String letter) {
        this.letter.setText(letter);
    }

    public void setPoints(int points) {
        this.points.setText(String.valueOf(points));
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        setBackground(new Color(238, 156, 96));
        game.getPlayers()[game.getPlayer2Turn()].setChosenTile(letter.getText());
        mainPanel.getCenterPanel().setChosenTileLabel(game.getPlayers()[game.getPlayer2Turn()].getChosenTile());

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setBackground(new Color(238, 191, 123));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setBackground(new Color(238, 224, 154));
    }
}
