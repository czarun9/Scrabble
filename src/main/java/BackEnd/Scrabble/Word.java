package BackEnd.Scrabble;

public class Word {

    private int finalScore;
    private int[] scores;
    private String word;
    private int[] letterMultipliers;
    private int[] wordMultipliers;

    public Word(String word, int[] letterMultipliers, int[] wordMultipliers, int[] scores) {
        this.word = word;
        this.letterMultipliers = letterMultipliers;
        this.wordMultipliers = wordMultipliers;
        this.scores = scores;
        countFinalScore();
    }

    public String getWord() {
        return word;
    }

    public void countFinalScore() {
        int[] tempLetterMultipliers = new int[letterMultipliers.length];
        int[] tempScores = new int[scores.length];
        int[] tempMultipliedScore = new int[scores.length];
        int iter = 0;
        for (int i = 0; i < letterMultipliers.length; i++) {
            if (letterMultipliers[i] != 0) {
                tempLetterMultipliers[iter] = letterMultipliers[i];
                tempScores[iter]=scores[i];
                tempMultipliedScore[iter] = tempLetterMultipliers[iter] * tempScores[iter];
                iter++;
            }
        }
        finalScore = 0;
        for (int i = 0; i < tempMultipliedScore.length; i++) {
            finalScore += tempMultipliedScore[i];
        }
        for (int i = 0; i < wordMultipliers.length; i++) {
            if(wordMultipliers[i]!=0){
                finalScore *=wordMultipliers[i];
            }
        }
    }

    public int getFinalScore() {
        return finalScore;
    }
}
