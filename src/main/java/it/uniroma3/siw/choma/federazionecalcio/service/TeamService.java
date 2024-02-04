package it.uniroma3.siw.choma.federazionecalcio.service;

import it.uniroma3.siw.choma.federazionecalcio.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
}
