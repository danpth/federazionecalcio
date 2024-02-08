package it.uniroma3.siw.choma.federazionecalcio.service;

import it.uniroma3.siw.choma.federazionecalcio.model.Team;
import it.uniroma3.siw.choma.federazionecalcio.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    public boolean alreadyExists(Team team) {
        return teamRepository.existsByName(team.getName());
    }

    @Transactional
    public void createNewTeam(Team team) {
        teamRepository.save(team);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void updateTeam(Team team) {
        teamRepository.save(team);
    }
}
