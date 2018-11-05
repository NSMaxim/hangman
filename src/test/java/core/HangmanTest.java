package core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HangmanTest {

    private static String wordToGuess = "Hydra";

    @Test
    public void newHangman() {
        Hangman hangman = new Hangman(wordToGuess);
        assertEquals(wordToGuess.toUpperCase(), hangman.getWordToGuess());
        assertEquals("-----", hangman.getWordStatus());
        assertEquals(0, hangman.getChosenLetters().size());
    }

    @Test
    public void preExistingGame() {
        Hangman hangman = new Hangman(wordToGuess, "---R-", "bxj", 4);
        assertEquals(wordToGuess.toUpperCase(), hangman.getWordToGuess());
        assertEquals("---R-", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());
        assertEquals("BXJ", StringUtils.join(hangman.getChosenLetters(), ""));
        assertEquals(HangmanStatus.IN_PROGRESS, hangman.currentStatus());
    }

    @Test
    public void guessLetters() {
        Hangman hangman = new Hangman(wordToGuess);

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("a"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(1, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(2, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("H"));
        assertEquals("H---A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());
    }

    @Test
    public void wonGame() {
        Hangman hangman = new Hangman(wordToGuess);

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("a"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(1, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("Y"));
        assertEquals("-Y--A", hangman.getWordStatus());
        assertEquals(2, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("h"));
        assertEquals("HY--A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("r"));
        assertEquals("HY-RA", hangman.getWordStatus());
        assertEquals(4, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.WON, hangman.guessLetter("d"));
        assertEquals("HYDRA", hangman.getWordStatus());
        assertEquals(5, hangman.getChosenLetters().size());
    }

    @Test
    public void lostGame() {
        Hangman hangman = new Hangman(wordToGuess);

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("a"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(1, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(2, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("v"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());

        assertEquals(HangmanStatus.LOST, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(3, hangman.getChosenLetters().size());
    }

}
