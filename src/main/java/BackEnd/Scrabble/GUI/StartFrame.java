package BackEnd.Scrabble.GUI;

import BackEnd.Scrabble.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class StartFrame extends JFrame {
    private Container container;
    private GridLayout layout;

    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton quitGameButton;
    private JLabel player1Label;
    private JLabel player2Label;
    private JTextField player1TextField;
    private JTextField player2TextField;

    StartFrame() {
        super("Start");
        this.container = getContentPane();
        setComponents();
        layout = new GridLayout(0, 1);
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(1280, 720);
        setResizable(false);

        setVisible(true);

    }

    private void setComponents() {
        //inicjalizacja przyciskow
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        quitGameButton = new JButton("Quit Game");

        //
        player1Label = new JLabel("Player1");
        player2Label = new JLabel("Player2");
        player1TextField = new JTextField();
        player2TextField = new JTextField();

        //dodanie listenerow
        newGameButton.addActionListener(this::newGameAction);
        loadGameButton.addActionListener(this::loadGameAction);
        quitGameButton.addActionListener(this::quitGameAction);
        //dodanie do przyciskow do containera
        container.add(newGameButton);
        container.add(new JLabel("Player1"));
        container.add(player1TextField);
        container.add(new JLabel("Player2"));
        container.add(player2TextField);
        container.add(loadGameButton);
        container.add(quitGameButton);
    }


    public static void main(String[] args) {
        new StartFrame();
    }

    public void newGameAction(ActionEvent event) {
        Game game = new Game(player1TextField.getText(), player2TextField.getText());
        game.start();
        game.setName("Thread-Game");
        new MainFrame(game);
        dispose();
    }

    public void loadGameAction(ActionEvent event) {

    }

    public void quitGameAction(ActionEvent event) {
        System.exit(0);
    }
}
