package it.uniroma3.siw.choma.federazionecalcio.controller;

import it.uniroma3.siw.choma.federazionecalcio.model.Player;
import it.uniroma3.siw.choma.federazionecalcio.model.Team;
import it.uniroma3.siw.choma.federazionecalcio.model.User;
import it.uniroma3.siw.choma.federazionecalcio.service.PlayerService;
import it.uniroma3.siw.choma.federazionecalcio.service.TeamService;
import it.uniroma3.siw.choma.federazionecalcio.service.UserService;
import it.uniroma3.siw.choma.federazionecalcio.validator.PlayerValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerValidator playerValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/players")
    public String showPlayers(Model model){
        model.addAttribute("players", playerService.findAll());
        return "players";
    }

    @GetMapping("/player/{id}")
    public String showPlayer(@PathVariable("id") Long id, Model model){
        Player player = playerService.findById(id);
        if(player == null) return "/errors/playerNotFoundError";

        model.addAttribute("player", player);
        return "player";
    }

    @GetMapping("/president/editPlayer/{id}")
    public String formEditPlayer(@PathVariable("id") Long id, Model model){
        Player player =  playerService.findById(id);
        if(player == null) return "errors/playerNotFoundError";
        model.addAttribute("player", player);
        return "president/editPlayer";
    }
    @PostMapping("/president/editPlayer/{id}")
    public String editPlayer(@PathVariable("id") Long id, @Valid @ModelAttribute("player") Player player, BindingResult bindingResult, Model model){
        playerValidator.validate(player, bindingResult);
        if(!bindingResult.hasErrors()){
            Player playerRetrieved = playerService.findById(id);
            if(playerRetrieved == null) return "errors/playerNotFoundError";
            playerRetrieved.setName(player.getName());
            playerRetrieved.setSurname(player.getSurname());
            playerRetrieved.setBirthDate(player.getBirthDate());
            playerRetrieved.setBirthPlace(player.getBirthPlace());
            playerRetrieved.setMembershipFirstDay(player.getMembershipFirstDay());
            playerRetrieved.setMembershipLastDay(player.getMembershipLastDay());
            playerService.updatePlayer(playerRetrieved);
            model.addAttribute("player", player);
            return "redirect:/player/" + playerRetrieved.getId();
        }
        return "president/editPlayer/"+id;
    }

    @GetMapping("/president/addPlayer")
    public String addPlayer(Model model){
        Player player =  new Player();
        model.addAttribute("player", player);
        return "president/addPlayer";
    }
    @PostMapping("/president/addPlayer")
    public String addPlayer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult, Model model){
        playerValidator.validate(player, bindingResult);
        User user = userService.getCurrentUser();
        if (user.getTeam() == null) return "errors/teamNotFoundError";
        if(!bindingResult.hasErrors()){
            Team team = user.getTeam();
            player.setTeam(team);
            playerService.addPlayer(player);
            model.addAttribute("player", player);
            return "president/playerAddedSuccess";
        }
        return "president/addPlayer";
    }


}
