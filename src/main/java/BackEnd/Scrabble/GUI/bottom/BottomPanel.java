package BackEnd.Scrabble.GUI.bottom;

import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.GUI.west.BoardSpace;
import BackEnd.Scrabble.Game;
import BackEnd.Scrabble.Word;

import com.google.gson.Gson;
import com.rits.cloning.Cloner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BottomPanel extends JPanel {

    private BorderLayout layout;
    private JPanel eastBottomPanel;
    private JButton turnOverButton;
    private JButton deleteWordButton;
    private Rack westBottomPanel;
    private Game game;
    private MainPanel mainPanel;
    private Gson gson;


    public BottomPanel(Game game, MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.layout = new BorderLayout();
        this.game = game;
        this.gson = new Gson();

        setLayout(layout);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setPreferredSize(new Dimension(1600, 100));
        setComponents();
    }

    public void setComponents() {
        westBottomPanel = new Rack(game, mainPanel);


        eastBottomPanel = new JPanel();
        eastBottomPanel.setBackground(Color.WHITE);
        eastBottomPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        eastBottomPanel.setPreferredSize(new Dimension(900, 100));

        turnOverButton = new JButton("End turn");
        deleteWordButton = new JButton("Delete word");

        deleteWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                for (int i = 0; i < 15; i++) {
                    if (mainPanel.getWestPanel().getSpacesForChange()[i] != null)
                        mainPanel.getWestPanel()
                                .getBoard()
                                [mainPanel.getWestPanel().getSpacesForChange()[i].getThisX()]
                                [mainPanel.getWestPanel().getSpacesForChange()[i].getThisY()] = mainPanel.getWestPanel().getSpacesForChange()[i];
                }

                mainPanel.getWestPanel().repaint();
                updateUI();
            }
        });

        turnOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Word word = mainPanel.getWestPanel().readWord1();

                if (word != null) {
                    mainPanel.getWestPanel().setSpacesForChange1();
                    mainPanel.getWestPanel().setIter(0);
                    game.getPlayers()[game.getPlayer2Turn()].setWord(word.getWord());
                    ///////////////////////
                    //TO DO


                    ///////////////
                    mainPanel.getCenterPanel().setTurnNameLabel(game.getPlayers()[game.getPlayer2Turn()].getPlayerName());
                    mainPanel.getWestPanel().setActiveBoardSpaces();
                    game.getPlayers()[game.getPlayer2Turn()].setPuttedLettersInTurn(0);
                    mainPanel.getCenterPanel().setChosenTileLabel("");

                    mainPanel.getEastPanel().getModel().addElement(game.getPlayers()[game.getPlayer2Turn()].getPlayerName() + " put word: " + word.getWord());
                    mainPanel.getEastPanel().getModel().addElement(game.getPlayers()[game.getPlayer2Turn()].getPlayerName() + " scores: " + word.getFinalScore());
                    game.getPlayers()[game.getPlayer2Turn()].addScore(word.getFinalScore());
                    mainPanel.getCenterPanel().refreshScores();

                    if (game.getPlayer2Turn() == 0) {
                        game.setPlayer2Turn(1);
                    } else {
                        game.setPlayer2Turn(0);
                    }
                    game.getPlayersSemaphore().release();
                    updateUI();
                    westBottomPanel.refreshRack();
                }
            }
        });
        eastBottomPanel.add(turnOverButton);
        eastBottomPanel.add(deleteWordButton);


        add(westBottomPanel, BorderLayout.LINE_START);
        add(eastBottomPanel, BorderLayout.LINE_END);
    }

    public Rack getWestBottomPanel() {
        return westBottomPanel;
    }
}
