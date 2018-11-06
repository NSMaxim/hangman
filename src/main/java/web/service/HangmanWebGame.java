package web.service;

import core.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import repository.GameRepository;
import repository.entity.Game;

import java.util.List;

/*
 * Sorts of a service used in the pages controller
 *
 * @author Maxim
 */

@Component
public class HangmanWebGame {

    private static final String hangmanModelName = "hangman";
    private static final String alphabetModelName = "alphabet";
    private static final String hintsModelName = "hints";
    private static final String gameIdModelName = "gameId";

    private final Alphabet alphabet = new EnglishAlphabet();
    private final Dictionary dictionary = new InMemoryDictionary();

    @Autowired
    private GameRepository gameRepository;


    public void newGame(Model model) {
        Hangman hangman = new Hangman(dictionary.random());
        Game hangmanGameStats = new Game(hangman);
        gameRepository.save(hangmanGameStats);
        updateModel(hangmanGameStats.getId(), model, hangman);
    }

    public void guessLetter(Model model, Long gameId, String chosenLetter) {
        Game hangmanGameStats = gameRepository.findDistinctById(gameId);

        if (hangmanGameStats == null) {
            newGame(model);
        } else {
            Hangman hangman = new Hangman(hangmanGameStats.getWordToGuess(),
                    hangmanGameStats.getWordStatus(),
                    hangmanGameStats.getChosenLetters(),
                    hangmanGameStats.getAttempts());

            if (HangmanStatus.IN_PROGRESS.equals(hangman.currentStatus())) {
                hangman.guessLetter(chosenLetter);

                hangmanGameStats.setChosenLetters(StringUtils.join(hangman.getChosenLetters(), ""));
                hangmanGameStats.setWordStatus(hangman.getWordStatus());
                hangmanGameStats.setAttempts(hangman.getFailedAttempts());

                gameRepository.save(hangmanGameStats);
            }

            updateModel(hangmanGameStats.getId(), model, hangman);
        }
    }

    private void updateModel(Long gameId, Model model, Hangman hangman) {
        model.addAttribute(gameIdModelName, gameId);
        model.addAttribute(alphabetModelName, alphabet);
        model.addAttribute(hintsModelName, hintsPerAttempts(hangman));
        model.addAttribute(hangmanModelName, hangman);
    }

    private List<String> hintsPerAttempts(Hangman hangman) {
        List<String> hints = dictionary.hintsFor(hangman.getWordToGuess());
        if (!hints.isEmpty()
                && hints.size() > hangman.getFailedAttempts()) {
            return hints.subList(0, hangman.getFailedAttempts() + 1);
        }
        return hints;
    }

}