import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HangmanTest {

    private static String wordToGuess = "Hydra";

    @Test
    public void newHangman() {
        Hangman hangman = new Hangman(wordToGuess);
        assertEquals(wordToGuess.toUpperCase(), hangman.getWordToGuess());
        assertEquals("-----", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());
    }

    @Test
    public void preExistingGame() {
        Hangman hangman = new Hangman(wordToGuess, "---R-", "bxj", 4);
        assertEquals(wordToGuess.toUpperCase(), hangman.getWordToGuess());
        assertEquals("---R-", hangman.getWordStatus());
        assertEquals(3, hangman.getWrongLetters().size());
        assertEquals("BXJ", StringUtils.join(hangman.getWrongLetters(), ""));
        assertEquals(HangmanStatus.IN_PROGRESS, hangman.currentStatus());
    }

    @Test
    public void guessLetters() {
        Hangman hangman = new Hangman(wordToGuess);

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("a"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(1, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("H"));
        assertEquals("H---A", hangman.getWordStatus());
        assertEquals(1, hangman.getWrongLetters().size());
    }

    @Test
    public void wonGame(){
        Hangman hangman = new Hangman(wordToGuess);

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("a"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("Y"));
        assertEquals("-Y--A", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("h"));
        assertEquals("HY--A", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("r"));
        assertEquals("HY-RA", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.WON, hangman.guessLetter("d"));
        assertEquals("HYDRA", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());
    }

    @Test
    public void lostGame(){
        Hangman hangman = new Hangman(wordToGuess);

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("a"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(0, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(1, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("v"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(2, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(3, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.IN_PROGRESS, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(4, hangman.getWrongLetters().size());

        assertEquals(HangmanStatus.LOST, hangman.guessLetter("b"));
        assertEquals("----A", hangman.getWordStatus());
        assertEquals(5, hangman.getWrongLetters().size());
    }

}
