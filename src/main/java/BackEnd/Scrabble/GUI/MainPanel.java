package BackEnd.Scrabble.GUI;

import BackEnd.Scrabble.GUI.bottom.BottomPanel;
import BackEnd.Scrabble.GUI.center.CenterPanel;
import BackEnd.Scrabble.GUI.west.WestPanel;
import BackEnd.Scrabble.GUI.east.EastPanel;
import BackEnd.Scrabble.Game;

import javax.swing.JPanel;
import java.awt.*;

public class MainPanel extends JPanel {

    private EastPanel eastPanel;
    private BottomPanel bottomPanel;
    private WestPanel westPanel;
    private CenterPanel centerPanel;

    public MainPanel(Game game) {
        eastPanel = new EastPanel(game,this);
        bottomPanel = new BottomPanel(game,this);
        westPanel = new WestPanel(game,this);
        centerPanel = new CenterPanel(game,this);
        setLayout(new BorderLayout());
        setPanels();
    }

    public void setPanels() {
        add(eastPanel, BorderLayout.LINE_END);
        add(bottomPanel, BorderLayout.PAGE_END);
        add(westPanel, BorderLayout.LINE_START);
        centerPanel.setPreferredSize((new Dimension(450, 700)));
        add(centerPanel, BorderLayout.CENTER);
    }

    public CenterPanel getCenterPanel(){
        return centerPanel;
    }

    public WestPanel getWestPanel() {
        return westPanel;
    }

    public EastPanel getEastPanel() {
        return eastPanel;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }
}

