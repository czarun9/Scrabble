package BackEnd.Scrabble.GUI.center;

import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterPanel extends JPanel {


    private BorderLayout layout;
    private Game game;
    private JLabel turnNameLabel;
    private JLabel chosenTileLabel;
    private JLabel player1Label;
    private JLabel player2Label;
    private MainPanel mainPanel;
    private JPanel scoresPanel;

    public CenterPanel(Game game, MainPanel mainPanel) {
        this.game = game;
        this.mainPanel = mainPanel;
        this.layout = new BorderLayout();
        setLayout(layout);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        configComponents();
    }

    public void configComponents() {
        turnNameLabel = new JLabel(this.game.getPlayers()[0].getPlayerName() + "'s turn");
        turnNameLabel.setPreferredSize(new Dimension(450, 50));
        turnNameLabel.setHorizontalAlignment(JLabel.CENTER);
        turnNameLabel.setFont(new Font("New Times Roman", Font.BOLD, 40));
        add(turnNameLabel, BorderLayout.NORTH);
        /////////////////////////////////////////////////////
        chosenTileLabel = new JLabel("");
        chosenTileLabel.setPreferredSize(new Dimension(450, 50));
        chosenTileLabel.setHorizontalAlignment(JLabel.CENTER);
        chosenTileLabel.setFont(new Font("New Times Roman", Font.BOLD, 40));
        add(chosenTileLabel, BorderLayout.CENTER);
        /////////////////////////////////////////////////////
        player1Label = new JLabel(game.getPlayers()[0].getPlayerName() + "'s score: " + game.getPlayers()[0].getScore());
        player2Label = new JLabel(game.getPlayers()[1].getPlayerName() + "'s score: " + game.getPlayers()[1].getScore());
        player1Label.setPreferredSize(new Dimension(100, 50));
        player2Label.setPreferredSize(new Dimension(100, 50));
        player1Label.setFont(new Font("New Times Roman", Font.ITALIC, 20));
        player2Label.setFont(new Font("New Times Roman", Font.ITALIC, 20));

        scoresPanel= new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel,BoxLayout.PAGE_AXIS));
        scoresPanel.add(player1Label);
        scoresPanel.add(player2Label);
        add(scoresPanel, BorderLayout.PAGE_END);

    }

    public void refreshScores(){
        if(game.getPlayer2Turn()==0){
            player1Label.setText(game.getPlayers()[0].getPlayerName() + "'s score: " + game.getPlayers()[0].getScore());
        }else{
            player2Label.setText(game.getPlayers()[1].getPlayerName() + "'s score: " + game.getPlayers()[1].getScore());
        }
    }

    public void setTurnNameLabel(String playerName) {
        this.turnNameLabel.setText(playerName + "'s turn");
    }

    public void setChosenTileLabel(String chosenTile) {
        this.chosenTileLabel.setText(chosenTile);
    }


}
