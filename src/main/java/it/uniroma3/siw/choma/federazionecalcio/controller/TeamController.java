package it.uniroma3.siw.choma.federazionecalcio.controller;

import it.uniroma3.siw.choma.federazionecalcio.model.Team;
import it.uniroma3.siw.choma.federazionecalcio.model.User;
import it.uniroma3.siw.choma.federazionecalcio.service.TeamService;
import it.uniroma3.siw.choma.federazionecalcio.service.UserService;
import it.uniroma3.siw.choma.federazionecalcio.validator.TeamValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamValidator teamValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/teams")
    public String showTeamsAdmin(Model model){
        model.addAttribute("teams", this.teamService.findAll());
        return "admin/teamsAdmin";
    }
    @GetMapping("/teams")
    public String showTeams(Model model){
        model.addAttribute("teams", this.teamService.findAll());
        return "teams";
    }
    @GetMapping("/admin/formNewTeam")
    public String formNewTeam(Model model){
        Team team =  new Team();
        model.addAttribute("team", team);
        return "admin/formNewTeam";
    }
    @GetMapping("/admin/editTeam/{id}")
    public String formNewTeam(@PathVariable("id") Long id, Model model){
        Team team =  teamService.findById(id);
        if(team == null) return "errors/teamNotFoundError";

        model.addAttribute("team", team);
        return "admin/adminEditTeam";
    }

    @PostMapping("/admin/editTeam/{id}")
    public String editTeam(@PathVariable("id") Long id, @Valid @ModelAttribute("team") Team team, BindingResult bindingResult, Model model){
        teamValidator.validate(team, bindingResult);
        if(!bindingResult.hasErrors()){
            Team teamRetrieved = teamService.findById(id);
            if(teamRetrieved == null) return "errors/teamNotFoundError";
            team.setId(teamRetrieved.getId());
            teamService.updateTeam(team);
            model.addAttribute("team", team);
            return "admin/teamAdmin";
        }else{
            return "admin/adminEditTeam/" + team.getId();
        }
    }


    @PostMapping("/admin/team")
    public String newTeam(@Valid @ModelAttribute("team") Team team, BindingResult bindingResult, Model model){
        teamValidator.validate(team, bindingResult);
        if(!bindingResult.hasErrors()){
            teamService.createNewTeam(team);
            model.addAttribute("team", team);
            return "admin/teamAdmin";
        }else{
            return "admin/formNewTeam";
        }
    }



    @GetMapping("/admin/team/{id}")
    public String getTeamAdmin(@PathVariable("id") Long id, Model model){
        Team team = teamService.findById(id);
        if(team == null) return "errors/teamNotFoundError";

        model.addAttribute("team", team);
        return "admin/teamAdmin";
    }

    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable("id") Long id, Model model){
        Team team = teamService.findById(id);
        if(team == null) return "errors/teamNotFoundError";

        model.addAttribute("team", team);
        return "team";
    }

    @PostMapping("/president/claim/{id}")
    public String claimTeam(@Valid @ModelAttribute("team") Team team, @PathVariable("id") Long id, Model model){
        Team teamRetrieved = teamService.findById(id);
        if(teamRetrieved == null) return "errors/teamNotFoundError";
        if (team.getPresident() != null) return "errors/teamAlreadyClaimed";
        User president = userService.getCurrentUser();
        president.setTeam(teamRetrieved);
        userService.updateUser(president);
        model.addAttribute("team", teamRetrieved);
        return "claimSuccessful";
    }

}
