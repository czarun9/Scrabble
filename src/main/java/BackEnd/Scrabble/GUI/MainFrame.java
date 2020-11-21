package BackEnd.Scrabble.GUI;

import BackEnd.Scrabble.Game;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(Game game) {
        super("Scrabble");
        setContentPane(new MainPanel(game));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(480, 320, 1600, 800);
        setVisible(true);
    }


}
