package core;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Main class for Hangman logic
 *
 * @author Maxim
 */
public class Hangman {
    private final static int MAX_FAILED_GUESSES = 6;
    private final static String unknownLetterHolder = "-";

    private String wordStatus;
    private String wordToGuess;
    private Set<String> chosenLetters;
    private int failedAttempts = 0;

    public Hangman() {
        //nothing to do here
    }

    public Hangman(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.wordStatus = wordToGuess.replaceAll("[a-zA-Z]", unknownLetterHolder);
        this.chosenLetters = new HashSet<>(27);
    }

    public Hangman(String wordToGuess, String wordStatus, String chosenLetters, int failedGuesses) {
        this.wordToGuess = wordToGuess;
        this.wordStatus = wordStatus;
        this.chosenLetters = new HashSet<>();
        for (char letter : chosenLetters.toCharArray()) {
            this.chosenLetters.add(String.valueOf(letter));
        }
        this.failedAttempts = failedGuesses;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Set<String> getChosenLetters() {
        return chosenLetters.stream().map(String::toUpperCase).collect(Collectors.toSet());
    }

    public void setChosenLetters(Set<String> chosenLetters) {
        this.chosenLetters = chosenLetters;
    }

    public String getWordStatus() {
        return wordStatus.toUpperCase();
    }

    public void setWordStatus(String wordStatus) {
        this.wordStatus = wordStatus;
    }

    public String getWordToGuess() {
        return wordToGuess.toUpperCase();
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
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
            failedAttempts++;
        }

        chosenLetters.add(normalizedLetter);
        return currentStatus();
    }

    public HangmanStatus currentStatus() {
        if (failedAttempts < MAX_FAILED_GUESSES && wordStatus.equalsIgnoreCase(wordToGuess)) {
            return HangmanStatus.WON;
        }
        if (failedAttempts == MAX_FAILED_GUESSES) {
            return HangmanStatus.LOST;
        }
        return HangmanStatus.IN_PROGRESS;
    }
}
