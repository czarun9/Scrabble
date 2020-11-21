package BackEnd.Scrabble.GUI.west;

import BackEnd.Scrabble.GUI.MainPanel;
import BackEnd.Scrabble.Game;
import BackEnd.Scrabble.Word;

import java.io.FileWriter;
import java.io.Reader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;


public class WestPanel extends JPanel {
    private final int BOARDSIZE = 15;
    private GridLayout layout;
    private BoardSpace[][] board;
    private BoardSpace[] spacesForChange;
    private int iter = 0;
    private Game game;
    private final Border WHITEBORDER = BorderFactory.createLineBorder(Color.WHITE);
    private final Border BLUEBORDER = BorderFactory.createLineBorder(Color.BLUE);
    private MainPanel mainPanel;
    private Reader reader;
    private FileWriter writer;

    public WestPanel(Game game, MainPanel mainPanel) {
        this.layout = new GridLayout(BOARDSIZE, BOARDSIZE);
        this.board = new BoardSpace[BOARDSIZE][BOARDSIZE];
        this.game = game;
        this.mainPanel = mainPanel;
        this.spacesForChange = new BoardSpace[BOARDSIZE];

        //////////JSON
        try (Reader reader = new FileReader("C:\\Users\\Cezary\\Desktop\\Semestr 4\\JTP\\Scrabble\\data\\boardspace.json")) {
            this.reader = reader;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("C:\\Users\\Cezary\\Desktop\\Semestr 4\\JTP\\Scrabble\\data\\boardspace.json")) {
            this.writer = writer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        ///////////////

        setLayout(layout);
        setPreferredSize(new Dimension(700, 700));

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setBackground(Color.WHITE);
        setBoard();
        setBoardColors();
        board[7][7].setCanBeLaid(true);
        board[7][7].setBorder(BLUEBORDER);

    }

    public void setBoard() {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                board[i][j] = new BoardSpace(game, mainPanel, i, j);
                add(board[i][j]);
            }
        }
    }

    public void refreshBoard() {

        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                add(board[i][j]);
            }
        }
    }

    public void setActiveBoardSpaces() {

        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                board[i][j].setCanBeLaid(false);
                board[i][j].setBorder(WHITEBORDER);
            }
        }

        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if (board[i][j].getLetter().getText() != "") {
                    if (i + 1 < BOARDSIZE && board[i + 1][j].getLetter().getText() == "") {
                        board[i + 1][j].setBorder(BLUEBORDER);
                        board[i + 1][j].setCanBeLaid(true);
                    }
                    if (j + 1 < BOARDSIZE && board[i][j + 1].getLetter().getText() == "") {
                        board[i][j + 1].setBorder(BLUEBORDER);
                        board[i][j + 1].setCanBeLaid(true);
                    }
                    if (i - 1 >= 0 && board[i - 1][j].getLetter().getText() == "") {
                        board[i - 1][j].setBorder(BLUEBORDER);
                        board[i - 1][j].setCanBeLaid(true);
                    }
                    if (j - 1 >= 0 && board[i][j - 1].getLetter().getText() == "") {
                        board[i][j - 1].setBorder(BLUEBORDER);
                        board[i][j - 1].setCanBeLaid(true);
                    }

                }
            }
        }
    }

    public void setActiveBoardSpaces2(int x1, int y1, int x2, int y2) {

        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                board[i][j].setCanBeLaid(false);
                board[i][j].setBorder(WHITEBORDER);
            }
        }

        if (x1 == x2) {
            for (int i = 0; i < BOARDSIZE; i++) {
                if (board[x1][i].getLetter().getText() != "") {
                    if (i - 1 > 0 && board[x1][i - 1].getLetter().getText() == "") {
                        board[x1][i - 1].setCanBeLaid(true);
                        board[x1][i - 1].setBorder(BLUEBORDER);
                    }
                    if (i + 1 < BOARDSIZE && board[x1][i + 1].getLetter().getText() == "") {
                        board[x1][i + 1].setCanBeLaid(true);
                        board[x1][i + 1].setBorder(BLUEBORDER);
                    }
                }
            }
        } else if (y1 == y2) {
            for (int i = 0; i < BOARDSIZE; i++) {
                if (board[i][y1].getLetter().getText() != "") {
                    if (i - 1 > 0 && board[i - 1][y1].getLetter().getText() == "") {
                        board[i - 1][y1].setCanBeLaid(true);
                        board[i - 1][y1].setBorder(BLUEBORDER);
                    }
                    if (i + 1 < BOARDSIZE && board[i + 1][y1].getLetter().getText() == "") {
                        board[i + 1][y1].setCanBeLaid(true);
                        board[i + 1][y1].setBorder(BLUEBORDER);
                    }
                }
            }
        }
    }


    public void setBoardColors() {

        Color dirtyYellow = new Color(247, 195, 21);
        Color lightBlue = new Color(81, 173, 243);
        Color red = new Color(183, 27, 27);
        Color darkBlue = new Color(37, 85, 199);

        for (int j = 0; j < BOARDSIZE; j += 7) { //czerwone pola
            for (int i = 0; i < BOARDSIZE; i += 7) {
                if (i != 7 || j != 7) {
                    board[j][i].setColor(red);
                    board[j][i].setWordMultiplier(3);
                }
            }
        }


        for (int i = 1; i < 14; i++) { //zolty
            board[i][i].setColor(dirtyYellow);
            board[i][14 - i].setColor(dirtyYellow);

            board[i][i].setWordMultiplier(2);
            board[i][14 - i].setWordMultiplier(2);
        }

        for (int j = 1; j < BOARDSIZE; j += 4) { //niebieskie
            for (int i = 1; i < BOARDSIZE; i += 4) {
                if ((i != 1 || j != 1) && (i != 1 || j != 13) && (i != 13 || j != 1) && (i != 13 || j != 13)) {
                    board[j][i].setColor(darkBlue);
                    board[j][i].setLetterMultiplier(3);

                }
            }
        }

        for (int j = 3; j < BOARDSIZE; j += 8) { //blekitne 1
            for (int i = 0; i < BOARDSIZE; i += 14) {
                board[j][i].setColor(lightBlue);
                board[i][j].setColor(lightBlue);
                board[j][i].setLetterMultiplier(2);
                board[i][j].setLetterMultiplier(2);
            }
        }


        for (int j = 2; j < 7; j += 4) { //blekitne 2
            for (int i = 6; i < 9; i += 2) {
                board[j][i].setColor(lightBlue);
                board[14 - j][i].setColor(lightBlue);
                board[i][j].setColor(lightBlue);
                board[i][14 - j].setColor(lightBlue);
                board[j][i].setLetterMultiplier(2);
                board[14 - j][i].setLetterMultiplier(2);
                board[i][j].setLetterMultiplier(2);
                board[i][14 - j].setLetterMultiplier(2);
            }
        }
        int i = 3;
        int j = 7;
        board[i][j].setColor(lightBlue);
        board[i][j].setLetterMultiplier(2);
        board[j][i].setColor(lightBlue);
        board[j][i].setLetterMultiplier(2);
        board[14 - i][j].setColor(lightBlue);
        board[14 - i][j].setLetterMultiplier(2);
        board[j][14 - i].setColor(lightBlue);
        board[j][14 - i].setLetterMultiplier(2);
        board[7][7].setLetterMultiplier(1);
        board[7][7].setWordMultiplier(1);

    }


    public Word readWord1() {
        int x1 = game.getPlayers()[game.getPlayer2Turn()].getFirstX();
        int y1 = game.getPlayers()[game.getPlayer2Turn()].getFirstY();
        int x2 = game.getPlayers()[game.getPlayer2Turn()].getSecondX();
        int y2 = game.getPlayers()[game.getPlayer2Turn()].getSecondY();
        String[] word = new String[BOARDSIZE];
        char[] charWord = new char[BOARDSIZE];
        int[] wordMultipliers = new int[BOARDSIZE];
        int[] letterMultipliers = new int[BOARDSIZE];
        int[] scores = new int[BOARDSIZE];
        char[] lastWord = new char[BOARDSIZE];
        int[] lastLetterMultipliers = new int[BOARDSIZE];
        int[] lastWordMultipliers = new int[BOARDSIZE];
        int[] lastScores = new int[BOARDSIZE];
        int iter = 0;

        if (x2 > -1 || y2 > -1) { //jesli polozono wiecej niz 1 kostke
            if (x2 == x1) { //jesli poziomo
                if (board[x2][y2 - 1].getLetter().getText() == "") { //jesli x2,y2 jest pierwszym klockiem slowa to wykonaj
                    for (int i = y2; i < BOARDSIZE; i++) {
                        word[i] = board[x1][i].getLetter().getText();

                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[x1][i].getPoints().getText());
                            wordMultipliers[iter] = board[x1][i].getWordMultiplier();
                            letterMultipliers[iter++] = board[x1][i].getLetterMultiplier();
                        } else {
                            String strWord = String.valueOf(charWord);
                            strWord = strWord.trim();
                            return new Word(strWord, letterMultipliers, wordMultipliers, scores);
                        }
                    }
                } else if (board[x2][y2 + 1].getLetter().getText() == "") { //jesli x2,y2 jest ostatnim klockiem
                    for (int i = y2; i < BOARDSIZE; i--) {
                        word[i] = board[x1][i].getLetter().getText();

                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[x1][i].getPoints().getText());
                            wordMultipliers[iter] = board[x1][i].getWordMultiplier();
                            letterMultipliers[iter++] = board[x1][i].getLetterMultiplier();
                        } else {
                            for (int j = 0; j < charWord.length; j++) {
                                lastWord[j] = charWord[charWord.length - 1 - j];
                                lastLetterMultipliers[j] = letterMultipliers[charWord.length - 1 - j];
                                lastWordMultipliers[j] = wordMultipliers[charWord.length - 1 - j];
                                lastScores[j] = scores[charWord.length - 1 - j];
                            }
                            String strWord = String.valueOf(lastWord);
                            strWord = strWord.trim();
                            return new Word(strWord, lastLetterMultipliers, lastWordMultipliers, lastScores);
                        }
                    }
                }


            }

            if (y2 == y1) {

                if (board[x2 - 1][y2].getLetter().getText() == "") { //jesli x2,y2 jest pierwszym klockiem slowa to wykonaj
                    for (int i = x2; i < BOARDSIZE; i++) {
                        word[i] = board[i][y2].getLetter().getText();
                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[i][y2].getPoints().getText());
                            wordMultipliers[iter] = board[i][y2].getWordMultiplier();
                            letterMultipliers[iter++] = board[i][y2].getLetterMultiplier();
                        } else {
                            String strWord = String.valueOf(charWord);
                            strWord = strWord.trim();
                            return new Word(strWord, letterMultipliers, wordMultipliers, scores);
                        }
                    }
                } else if (board[x2 + 1][y2].getLetter().getText() == "") { //jesli x2,y2 jest pierwszym klockiem
                    for (int i = x2; i < BOARDSIZE; i--) {
                        word[i] = board[i][y2].getLetter().getText();
                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[i][y2].getPoints().getText());
                            wordMultipliers[iter] = board[i][y2].getWordMultiplier();
                            letterMultipliers[iter++] = board[i][y2].getLetterMultiplier();
                        } else {
                            for (int j = 0; j < charWord.length; j++) {
                                lastWord[j] = charWord[charWord.length - 1 - j];
                                lastLetterMultipliers[j] = letterMultipliers[charWord.length - 1 - j];
                                lastWordMultipliers[j] = wordMultipliers[charWord.length - 1 - j];
                                lastScores[j] = scores[charWord.length - 1 - j];
                            }
                            String strWord = String.valueOf(lastWord);
                            strWord = strWord.trim();
                            return new Word(strWord, lastLetterMultipliers, lastWordMultipliers, lastScores);
                        }
                    }


                }
            }

        }


        if (x2 == -1) {
            if (board[x1][y1 + 1].getLetter().getText() == "" || board[x1][y1 - 1].getLetter().getText() == "") { //jesli nad i pod nie ma mozliwych slow
                if (board[x1 + 1][y1].getLetter().getText() != "" && x1 + 1 < BOARDSIZE && board[x1 - 1][y1].getLetter().getText() == "") { //jesli dostawiony klocek jest pierwszym po lewo
                    for (int i = x1; i < BOARDSIZE; i++) {
                        word[i] = board[i][y1].getLetter().getText();
                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[i][y1].getPoints().getText());
                            wordMultipliers[iter] = board[i][y1].getWordMultiplier();
                            letterMultipliers[iter++] = board[i][y1].getLetterMultiplier();
                        } else {
                            String strWord = String.valueOf(charWord);
                            strWord = strWord.trim();
                            return new Word(strWord, letterMultipliers, wordMultipliers, scores);
                        }
                    }
                }
                if (board[x1 - 1][y1].getLetter().getText() != "" && x1 - 1 >= 0 && board[x1 + 1][y1].getLetter().getText() == "") { //jesli dostawiony klocek jest ostatnim po prawo
                    for (int i = x1; i < BOARDSIZE; i--) {
                        word[i] = board[i][y1].getLetter().getText();
                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[i][y1].getPoints().getText());
                            wordMultipliers[iter] = board[i][y1].getWordMultiplier();
                            letterMultipliers[iter++] = board[i][y1].getLetterMultiplier();
                        } else {

                            for (int j = 0; j < charWord.length; j++) {
                                lastWord[j] = charWord[charWord.length - 1 - j];
                                lastLetterMultipliers[j] = letterMultipliers[charWord.length - 1 - j];
                                lastWordMultipliers[j] = wordMultipliers[charWord.length - 1 - j];
                                lastScores[j] = scores[charWord.length - 1 - j];
                            }
                            String strWord = String.valueOf(lastWord);
                            strWord = strWord.trim();
                            return new Word(strWord, lastLetterMultipliers, lastWordMultipliers, lastScores);
                        }
                    }
                }

            }
            if (board[x1 + 1][y1].getLetter().getText() == "" || board[x1 - 1][y1].getLetter().getText() == "") {
                if (board[x1][y1 + 1].getLetter().getText() != "" && y1 + 1 < BOARDSIZE && board[x1][y1 - 1].getLetter().getText() == "") { //jesli dostawiony klocek jest pierwszym po lewo
                    for (int i = y1; i < BOARDSIZE; i++) {
                        word[i] = board[x1][i].getLetter().getText();
                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[x1][i].getPoints().getText());
                            wordMultipliers[iter] = board[x1][i].getWordMultiplier();
                            letterMultipliers[iter++] = board[x1][i].getLetterMultiplier();
                        } else {
                            String strWord = String.valueOf(charWord);
                            strWord = strWord.trim();
                            return new Word(strWord, letterMultipliers, wordMultipliers, scores);
                        }
                    }
                }
                if (board[x1][y1 - 1].getLetter().getText() != "" && x1 - 1 >= 0 && board[x1][y1 + 1].getLetter().getText() == "") { //jesli dostawiony klocek jest ostatnim po prawo
                    for (int i = y1; i < BOARDSIZE; i--) {
                        word[i] = board[x1][i].getLetter().getText();
                        if (word[i] != "") {
                            charWord[iter] = word[i].charAt(0);
                            scores[iter] = Integer.parseInt(board[x1][i].getPoints().getText());
                            wordMultipliers[iter] = board[x1][i].getWordMultiplier();
                            letterMultipliers[iter++] = board[x1][i].getLetterMultiplier();
                        } else {
                            for (int j = 0; j < charWord.length; j++) {
                                lastWord[j] = charWord[charWord.length - 1 - j];
                                lastLetterMultipliers[j] = letterMultipliers[charWord.length - 1 - j];
                                lastWordMultipliers[j] = wordMultipliers[charWord.length - 1 - j];
                                lastScores[j] = scores[charWord.length - 1 - j];
                            }
                            String strWord = String.valueOf(lastWord);
                            strWord = strWord.trim();
                            return new Word(strWord, lastLetterMultipliers, lastWordMultipliers, lastScores);
                        }
                    }
                }
            }

        }
        return null;
    }

    public BoardSpace[][] getBoard() {
        return board;
    }

    public BoardSpace[] getSpacesForChange() {
        return spacesForChange;
    }

    public void setSpacesForChange1() {
        for (int i = 0; i < spacesForChange.length; i++) {
            this.spacesForChange[i] = null;
        }
    }

    public void setSpacesForChange(BoardSpace spacesForChange) {
        this.spacesForChange[iter++] = spacesForChange;
    }

    public void setIter(int iter) {
        this.iter = iter;
    }

    public Reader getReader() {
        return reader;
    }

    public FileWriter getWriter() {
        return writer;
    }
}