package it.uniroma3.siw.choma.federazionecalcio.controller;

import it.uniroma3.siw.choma.federazionecalcio.model.User;
import it.uniroma3.siw.choma.federazionecalcio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
    @Autowired
    private UserService userService;
    @ModelAttribute("userAuthDetails")
    public UserDetails getUserAuthDetails() {
        UserDetails user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }
    @ModelAttribute("globalUser")
    public User getUser() {
        return userService.getCurrentUser();
    }
}
