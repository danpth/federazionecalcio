package it.uniroma3.siw.choma.federazionecalcio.service;

import it.uniroma3.siw.choma.federazionecalcio.model.User;
import it.uniroma3.siw.choma.federazionecalcio.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected CredentialsService credentialsService;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public User getCurrentUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String username = authentication.getName();
            user = credentialsService.getCredentials(username).getUser();
        }
        return user;
    }

    /*Method that checks if a User can add a Team (if he is an ADMIN)
    */
    public boolean canAddTeam(User user){
        return true;
    }

    /*Method that checks if a User can add a Player (if he is a PRESIDENT)
     */
    public boolean canAddPlayer(User user){
        return true;
    }

    @Transactional
    public boolean alreadyExists(User user){
        return userRepository.existsByNameAndSurnameAndEmail(user.getName(),user.getSurname(),user.getEmail());
    }
    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }
}