import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Main class for Hangman logic
 *
 * @author Maxim
 */
public class Hangman {
    private final static int MAX_FAILED_GUESSES = 5;

    private String wordStatus;
    private String wordToGuess;
    private Set<String> wrongLetters;
    private int failedGuesses = 0;

    public Hangman(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.wordStatus = wordToGuess.replaceAll("[a-zA-Z]", "-");
        this.wrongLetters = new HashSet<>(27);
    }

    public Hangman(String wordToGuess, String wordStatus, String wrongLetters, int failedGuesses) {
        this.wordToGuess = wordToGuess;
        this.wordStatus = wordStatus;
        this.wrongLetters = new HashSet<>();
        for (char letter : wrongLetters.toCharArray()) {
            this.wrongLetters.add(String.valueOf(letter));
        }
        this.failedGuesses = failedGuesses;
    }

    public int getFailedGuesses() {
        return failedGuesses;
    }

    public Set<String> getWrongLetters() {
        return wrongLetters.stream().map(String::toUpperCase).collect(Collectors.toSet());
    }

    public String getWordStatus() {
        return wordStatus.toUpperCase();
    }

    public String getWordToGuess() {
        return wordToGuess.toUpperCase();
    }

    public HangmanStatus guessLetter(String letter) {
        String normalizedLetter = letter.toUpperCase();
        if (getWordToGuess().contains(normalizedLetter)) {
            for (int i = 0; i < getWordToGuess().length(); i++) {
                if (normalizedLetter.equals(String.valueOf(getWordToGuess().charAt(i)))) {
                    wordStatus = getWordStatus().substring(0, i) + normalizedLetter + getWordStatus().substring(i + 1);
                }
            }
        } else {
            failedGuesses++;
            wrongLetters.add(normalizedLetter);
        }

        return currentStatus();
    }

    public HangmanStatus currentStatus() {
        if (failedGuesses < MAX_FAILED_GUESSES && wordStatus.equalsIgnoreCase(wordToGuess)) {
            return HangmanStatus.WON;
        }
        if (failedGuesses == MAX_FAILED_GUESSES) {
            return HangmanStatus.LOST;
        }
        return HangmanStatus.IN_PROGRESS;
    }
}
