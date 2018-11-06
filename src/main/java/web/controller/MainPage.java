package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.HangmanWebGame;

/*
 * Main controller that works with index page
 *
 * uses HangmanWebGame as a main service
 *
 * @author Maxim
 */

@Controller
public class MainPage {

    @Autowired
    private HangmanWebGame hangmanWebGame;

    @GetMapping("/")
    public String index(Model model) {
        hangmanWebGame.newGame(model);
        return "index";
    }

    @GetMapping("/game")
    public String choseWord(@RequestParam(name = "gameId") Long gameId,
                            @RequestParam(name = "chosenLetter") String chosenLetter,
                            Model model) {
        hangmanWebGame.guessLetter(model, gameId, chosenLetter);
        return "index";
    }

}
