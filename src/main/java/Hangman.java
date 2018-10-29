import java.util.ArrayList;
import java.util.List;

public class Hangman {
    private final static int MAX_FAILED_GUESSES = 5;

    private String wordStatus;
    private String wordToGuess;
    private List<String> wrongLetters;
    private int failedGuesses = 0;

    public Hangman(String wordToGuess) {
        this.wordToGuess = wordToGuess.toUpperCase();
        this.wordStatus = wordToGuess.replaceAll("[a-zA-Z]", "-");
        this.wrongLetters = new ArrayList<String>(27);
    }

    public Hangman(String wordToGuess, String wordStatus, String wrongLetters, int failedGuesses) {
        this.wordToGuess = wordToGuess.toUpperCase();
        this.wordStatus = wordStatus.toUpperCase();
        this.wrongLetters = new ArrayList<String>();
        for (char letter : wrongLetters.toUpperCase().toCharArray()) {
            this.wrongLetters.add(String.valueOf(letter));
        }
        this.failedGuesses = failedGuesses;
    }

    public int getFailedGuesses() {
        return failedGuesses;
    }

    public List<String> getWrongLetters() {
        return wrongLetters;
    }

    public String getWordStatus() {
        return wordStatus;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public HangmanStatus guessLetter(String letter) {
        String normalizedLetter = letter.toUpperCase();
        if (wordToGuess.contains(normalizedLetter)) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (normalizedLetter.equals(String.valueOf(wordToGuess.charAt(i)))) {
                    wordStatus = wordStatus.substring(0, i) + normalizedLetter + wordStatus.substring(i + 1);
                }
            }
        } else {
            failedGuesses++;
            wrongLetters.add(normalizedLetter);
        }

        return currentStatus();
    }

    public HangmanStatus currentStatus() {
        if (failedGuesses < MAX_FAILED_GUESSES && wordStatus.equals(wordToGuess)) {
            return HangmanStatus.WON;
        }
        if (failedGuesses == MAX_FAILED_GUESSES) {
            return HangmanStatus.LOST;
        }
        return HangmanStatus.IN_PROGRESS;
    }
}
