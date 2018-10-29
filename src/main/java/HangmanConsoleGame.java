import com.sun.deploy.util.StringUtils;

import java.util.List;
import java.util.Scanner;

public class HangmanConsoleGame {

    public static void main(String[] args) {
        Dictionary dictionary = new BaseDictionaryImpl();
        Alphabet alphabet = new Alphabet();
        Hangman hangman = new Hangman(dictionary.random());
        List<String> hints = dictionary.hintsFor(hangman.getWordToGuess());

        Scanner scanner = new Scanner(System.in);

        while (hangman.currentStatus() == HangmanStatus.IN_PROGRESS) {
            System.out.println("----------------- Hangman, make a turn -------------------");

            System.out.println("Current status: " + hangman.getWordStatus() + "\n");
            System.out.println("Failed attempts: " + hangman.getFailedGuesses() + "\n");

            showHints(hangman, hints);

            System.out.println("\nLetters to chose from: " + StringUtils.join(alphabet.availableLetters(hangman.getWrongLetters()), " "));
            System.out.println("Wrong letters: " + StringUtils.join(hangman.getWrongLetters(), " "));

            System.out.println("----------------- Choose a letter to continue -------------------");
            System.out.print("Next letter is - ");
            String chosenLetter = scanner.nextLine();
            hangman.guessLetter(chosenLetter);
        }

        if (HangmanStatus.WON.equals(hangman.currentStatus())) {
            System.out.println("\nCongratulations you WON!");
        } else {
            System.out.println("\nLooser!");
        }
    }

    private static void showHints(Hangman hangman, List<String> hints) {
        System.out.println("Hints:");
        for (int i = -1; i < hangman.getFailedGuesses(); i++) {
            int actualHintNumber = i + 1;
            if (actualHintNumber >= hints.size()) {
                break;
            }
            System.out.println(actualHintNumber + 1 + ". " + hints.get(actualHintNumber));
        }
    }

}
