package BackEnd.Scrabble.GUI.bottom;

import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rack extends JPanel {

    private GridLayout layout;
    private List<LetterPanel> rackList;
    private MainPanel mainPanel;

    private Game game;

    public Rack(Game game, MainPanel mainPanel) {
        this.game = game;
        this.mainPanel = mainPanel;
        rackList = new ArrayList<>(7);
        layout = new GridLayout(1, rackList.size());
        layout.setVgap(20);
        layout.setHgap(20);
        setLayout(layout);

        setBackground(new Color(0, 40, 20));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setPreferredSize(new Dimension(700, 100));
        setRack();
    }

    public void setRack() {
        for (int i = 0; i < 7; i++) {
            rackList.add(new LetterPanel(game.getPlayers()[0].getRack().get(i), game, mainPanel));
        }

        for (JPanel letter : rackList) {
            add(letter);
        }

    }

    public void refreshRack() {
        for (int i = 0; i < 7; i++) {
            String tempLetter = game.getPlayers()[game.getPlayer2Turn()].getRack().get(i).getLetter();
            rackList.get(i).setLetter(tempLetter);
            rackList.get(i).setPoints(game.getScrabbleSet().getPoints(tempLetter));
        }
    }


}
