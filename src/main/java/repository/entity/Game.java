package repository.entity;

import core.Hangman;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Game entity that is used together with repository
 *
 * @author Maxim
 */

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String wordToGuess;
    private String wordStatus;
    private String chosenLetters;
    private int attempts;

    protected Game() {
        //nothing to do here
    }

    public Game(Hangman hangman) {
        this.wordStatus = hangman.getWordStatus();
        this.wordToGuess = hangman.getWordToGuess();
        this.chosenLetters = StringUtils.join(hangman.getChosenLetters(), "");
        this.attempts = hangman.getFailedAttempts();
    }

    public Long getId() {
        return id;
    }

    public String getChosenLetters() {
        return chosenLetters;
    }

    public void setChosenLetters(String chosenLetters) {
        this.chosenLetters = chosenLetters;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public String getWordStatus() {
        return wordStatus;
    }

    public void setWordStatus(String wordStatus) {
        this.wordStatus = wordStatus;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

}
