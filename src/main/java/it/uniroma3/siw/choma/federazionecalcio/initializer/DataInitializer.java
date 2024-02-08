package it.uniroma3.siw.choma.federazionecalcio.initializer;

import it.uniroma3.siw.choma.federazionecalcio.model.Credentials;
import it.uniroma3.siw.choma.federazionecalcio.model.Player;
import it.uniroma3.siw.choma.federazionecalcio.model.Team;
import it.uniroma3.siw.choma.federazionecalcio.model.User;
import it.uniroma3.siw.choma.federazionecalcio.service.CredentialsService;
import it.uniroma3.siw.choma.federazionecalcio.service.PlayerService;
import it.uniroma3.siw.choma.federazionecalcio.service.TeamService;
import it.uniroma3.siw.choma.federazionecalcio.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataInitializer {
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Transactional
    @PostConstruct
    public void init() {
        // Creazione degli utenti
        User adminUser = new User();
        adminUser.setName("Admin");
        adminUser.setSurname("Admin");
        adminUser.setEmail("admin@example.com");
        userService.save(adminUser);

        Credentials adminCredentials = new Credentials();
        adminCredentials.setUsername("admin");
        adminCredentials.setPassword(passwordEncoder.encode("admin"));
        adminCredentials.setUser(adminUser);
        credentialsService.saveCredentialsAdmin(adminCredentials);

        User presidentUser = new User();
        presidentUser.setName("Daniel");
        presidentUser.setSurname("Choma");
        presidentUser.setEmail("daniel@example.com");
        presidentUser.setFiscalCode("CHMDNL95L01Z127I");
        presidentUser.setBirthDate("01/07/1995");
        presidentUser.setBirthPlace("Poland");
        userService.save(presidentUser);

        Credentials presidentCredentials = new Credentials();
        presidentCredentials.setUsername("pre");
        presidentCredentials.setPassword(passwordEncoder.encode("pre"));
        presidentCredentials.setUser(presidentUser);
        credentialsService.saveCredentials(presidentCredentials);

        // Inizializzazione dei team
        Team interMilan = new Team();
        interMilan.setAddress("Milano");
        interMilan.setName("FC Inter Milano");
        interMilan.setYearOfFoundation(1908);
        teamService.createNewTeam(interMilan);

        Team acMilan = new Team();
        acMilan.setAddress("Milano");
        acMilan.setName("AC Milan");
        acMilan.setYearOfFoundation(1899);
        teamService.createNewTeam(acMilan);

        Team asRoma = new Team();
        asRoma.setAddress("Roma");
        asRoma.setName("AS Roma");
        asRoma.setYearOfFoundation(1927);
        teamService.createNewTeam(asRoma);

        Team ssLazio = new Team();
        ssLazio.setAddress("Roma");
        ssLazio.setName("SS Lazio");
        ssLazio.setYearOfFoundation(1901);
        teamService.createNewTeam(ssLazio);

        // Creazione dei giocatori
        Player messi = new Player();
        messi.setBirthDate("12/03/1995");
        messi.setBirthPlace("Milano");
        messi.setMembershipFirstDay("01/01/2023");
        messi.setMembershipLastDay("31/12/2024");
        messi.setName("Lionel");
        messi.setSurname("Messi");
        messi.setTeam(interMilan); // ID del FC Inter Milano
        playerService.addPlayer(messi);

        Player ramos = new Player();
        ramos.setBirthDate("05/09/1998");
        ramos.setBirthPlace("Madrid");
        ramos.setMembershipFirstDay("01/01/2023");
        ramos.setMembershipLastDay("31/12/2024");
        ramos.setName("Sergio");
        ramos.setSurname("Ramos");
        ramos.setTeam(interMilan);
        playerService.addPlayer(ramos);

        Player totti = new Player();
        totti.setBirthDate("30/11/1990");
        totti.setBirthPlace("Roma");
        totti.setMembershipFirstDay("01/01/2023");
        totti.setMembershipLastDay("31/12/2024");
        totti.setName("Francesco");
        totti.setSurname("Totti");
        totti.setTeam(asRoma);
        playerService.addPlayer(totti);

        Player insigne = new Player();
        insigne.setBirthDate("22/07/1994");
        insigne.setBirthPlace("Napoli");
        insigne.setMembershipFirstDay("01/01/2023");
        insigne.setMembershipLastDay("31/12/2024");
        insigne.setName("Lorenzo");
        insigne.setSurname("Insigne");
        insigne.setTeam(asRoma);
        playerService.addPlayer(insigne);

        Player immobile = new Player();
        immobile.setBirthDate("28/10/1987");
        immobile.setBirthPlace("Roma");
        immobile.setMembershipFirstDay("01/01/2023");
        immobile.setMembershipLastDay("31/12/2024");
        immobile.setName("Ciro");
        immobile.setSurname("Immobile");
        immobile.setTeam(ssLazio);
        playerService.addPlayer(immobile);

    }
}
