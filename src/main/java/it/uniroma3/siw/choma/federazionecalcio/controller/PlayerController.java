package it.uniroma3.siw.choma.federazionecalcio.controller;

import it.uniroma3.siw.choma.federazionecalcio.service.PlayerService;
import it.uniroma3.siw.choma.federazionecalcio.validator.PlayerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerValidator playerValidator;

    @GetMapping("/players")
    public String showPlayers(Model model){
        model.addAttribute("players", this.playerService.findAll());
        return "players";
    }
}
