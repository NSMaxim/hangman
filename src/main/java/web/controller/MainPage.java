package web.controller;

import core.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.GameRepository;
import repository.entity.Game;

import java.util.List;

@Controller
public class MainPage {

    private static final String hangmanModelName = "hangman";
    private static final String alphabetModelName = "alphabet";
    private static final String hintsModelName = "hints";

    private final Alphabet alphabet = new EnglishAlphabet();
    private final Dictionary dictionary = new InMemoryDictionary();

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("model no exists");
        Hangman hangman = new Hangman(dictionary.random());

        Game hangmanGameStats = new Game(hangman);
        gameRepository.save(hangmanGameStats);

        updateModel(hangmanGameStats.getId(), model, hangman);
        return "index";
    }

    @GetMapping("/game")
    public String choseWord(@RequestParam(name = "gameId") Long gameId,
                            @RequestParam(name = "chosenLetter") String chosenLetter,
                            Model model) {
        Game hangmanGameStats = gameRepository.findDistinctById(gameId);
        Hangman hangman = new Hangman(hangmanGameStats.getWordToGuess(),
                hangmanGameStats.getWordStatus(),
                hangmanGameStats.getChosenLetters(),
                hangmanGameStats.getAttempts());

        hangman.guessLetter(chosenLetter);

        hangmanGameStats.setChosenLetters(StringUtils.join(hangman.getChosenLetters(), ""));
        hangmanGameStats.setWordStatus(hangman.getWordStatus());
        hangmanGameStats.setAttempts(hangman.getFailedAttempts());

        gameRepository.save(hangmanGameStats);

        updateModel(hangmanGameStats.getId(), model, hangman);
        return "index";
    }

    private void updateModel(Long gameId, Model model, Hangman hangman) {
        model.addAttribute("gameId", gameId);
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
