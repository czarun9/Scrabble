package BackEnd.Scrabble;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ScrabbleSet {

    private Map<Character, Integer> scrabbleSet;
    private Map<Character, Integer> scrabblePointMap;
    private char[] alphabet;

    public ScrabbleSet() {
        scrabbleSet = new HashMap<>();
        scrabblePointMap = new HashMap<>();
        fillScrabbleSet();
        fillScrabblePointMap();
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWYZ_".toCharArray();
    }

    public void fillScrabbleSet() {
        scrabbleSet.put('A', 9);
        scrabbleSet.put('B', 2);
        scrabbleSet.put('C', 2);
        scrabbleSet.put('D', 4);
        scrabbleSet.put('E', 12);
        scrabbleSet.put('F', 2);
        scrabbleSet.put('G', 3);
        scrabbleSet.put('H', 2);
        scrabbleSet.put('I', 9);
        scrabbleSet.put('J', 1);
        scrabbleSet.put('K', 1);
        scrabbleSet.put('L', 4);
        scrabbleSet.put('M', 2);
        scrabbleSet.put('N', 6);
        scrabbleSet.put('O', 8);
        scrabbleSet.put('P', 2);
        scrabbleSet.put('Q', 1);
        scrabbleSet.put('R', 6);
        scrabbleSet.put('S', 4);
        scrabbleSet.put('T', 6);
        scrabbleSet.put('U', 4);
        scrabbleSet.put('V', 2);
        scrabbleSet.put('W', 2);
        scrabbleSet.put('X', 1);
        scrabbleSet.put('Y', 2);
        scrabbleSet.put('Z', 1);
        scrabbleSet.put('_', 2);
    }

    public void fillScrabblePointMap() {
        scrabblePointMap.put('A', 1);
        scrabblePointMap.put('B', 3);
        scrabblePointMap.put('C', 3);
        scrabblePointMap.put('D', 2);
        scrabblePointMap.put('E', 1);
        scrabblePointMap.put('F', 4);
        scrabblePointMap.put('G', 2);
        scrabblePointMap.put('H', 4);
        scrabblePointMap.put('I', 1);
        scrabblePointMap.put('J', 8);
        scrabblePointMap.put('K', 5);
        scrabblePointMap.put('L', 1);
        scrabblePointMap.put('M', 3);
        scrabblePointMap.put('N', 1);
        scrabblePointMap.put('O', 1);
        scrabblePointMap.put('P', 3);
        scrabblePointMap.put('R', 1);
        scrabblePointMap.put('Q', 10);
        scrabblePointMap.put('S', 1);
        scrabblePointMap.put('T', 1);
        scrabblePointMap.put('U', 1);
        scrabblePointMap.put('V', 4);
        scrabblePointMap.put('W', 4);
        scrabblePointMap.put('X', 8);
        scrabblePointMap.put('Y', 4);
        scrabblePointMap.put('Z', 10);
        scrabblePointMap.put('_', 0);

    }

    public Block drawBlock() {
        Random number = new Random();
        int position = number.nextInt(alphabet.length); //losuje pozycje od 0 do dlugosci alfabetu
        scrabbleSet.replace(alphabet[position], scrabbleSet.get(alphabet[position]), scrabbleSet.get(alphabet[position]) - 1);  //zmniejszam ilosc tilesow w worku
        return new Block(String.valueOf(alphabet[position]), scrabblePointMap.get(alphabet[position]));
    }

    public int getPoints(String letter){
        return scrabblePointMap.get(letter.charAt(0));
    }
}
