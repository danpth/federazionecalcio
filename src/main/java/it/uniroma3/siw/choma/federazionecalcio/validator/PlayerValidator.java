package it.uniroma3.siw.choma.federazionecalcio.validator;

import it.uniroma3.siw.choma.federazionecalcio.model.Player;
import it.uniroma3.siw.choma.federazionecalcio.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {
    @Autowired
    private PlayerService playerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;
        if(playerService.alreadyExists(player)){
            errors.reject("player.duplicate");
        }
    }
}
