package BackEnd.Scrabble.GUI.east;

import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.Game;

import javax.swing.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class EastPanel extends JPanel {

    private List<JLabel> messageList;
    private GridLayout layout;
    private Game game;
    private JList actionList;
    private DefaultListModel model= new DefaultListModel();

    public EastPanel(Game game, MainPanel mainPanel) {
        this.layout = new GridLayout(0,1);
        this.messageList=new LinkedList<>();
        this.game = game;

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setPreferredSize(new Dimension(450,700));
        setLayout(layout);

        model.addElement("Start the game.");
        actionList=new JList(model);
        add(actionList);

    }



    public DefaultListModel getModel() {
        return model;
    }
}
