package core;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

/*
 * A simple console app to cover as an Hangman sample app
 *
 * @author Maxim
 */
public class HangmanConsoleGame {

    public static void main(String[] args) {
        Dictionary dictionary = new InMemoryDictionary();
        Alphabet alphabet = new EnglishAlphabet();
        Hangman hangman = new Hangman(dictionary.random());
        List<String> hints = dictionary.hintsFor(hangman.getWordToGuess());

        Scanner scanner = new Scanner(System.in);

        while (hangman.currentStatus() == HangmanStatus.IN_PROGRESS) {
            printToScreen("----------------- core.Hangman, make a turn -------------------");

            printToScreen("Current status: %s \n", hangman.getWordStatus());
            printToScreen("Failed attempts: %s \n", hangman.getFailedAttempts());

            showHints(hangman, hints);

            printToScreen("Letters to chose from: %s", StringUtils.join(alphabet.availableLetters(hangman.getChosenLetters()), " "));
            printToScreen("Chosen letters: %s", StringUtils.join(hangman.getChosenLetters(), " "));

            printToScreen("----------------- Choose a letter to continue -------------------");
            printToScreen("Next letter is - ");
            String chosenLetter = scanner.nextLine();
            hangman.guessLetter(chosenLetter);
        }

        if (HangmanStatus.WON.equals(hangman.currentStatus())) {
            printToScreen("\nCongratulations you WON!");
        } else {
            printToScreen("\nLooser! ");
        }
        printToScreen("Word was - %s", hangman.getWordToGuess());
    }

    private static void printToScreen(String message, Object... arguments) {
        System.out.println(String.format(message, arguments));
    }

    private static void showHints(Hangman hangman, List<String> hints) {
        printToScreen("Hints:");

        if (hints.isEmpty()) {
            printToScreen("No hints for this word");
            return;
        }

        for (int i = -1; i < hangman.getFailedAttempts(); i++) {
            int actualHintNumber = i + 1;
            if (actualHintNumber >= hints.size()) {
                break;
            }
            System.out.println(actualHintNumber + 1 + ". " + hints.get(actualHintNumber));
        }
        printToScreen("");
    }

}
