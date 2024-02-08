package it.uniroma3.siw.choma.federazionecalcio.validator;

import it.uniroma3.siw.choma.federazionecalcio.model.Team;
import it.uniroma3.siw.choma.federazionecalcio.service.TeamService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamValidator implements Validator {
    @Autowired
    private TeamService teamService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Team team = (Team) target;
        if (teamService.alreadyExists(team)){
            errors.reject("team.duplicate");
        }
    }
}
