package it.uniroma3.siw.choma.federazionecalcio.service;

import it.uniroma3.siw.choma.federazionecalcio.model.Player;
import it.uniroma3.siw.choma.federazionecalcio.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public boolean alreadyExists(Player player) {
        return playerRepository.existsByNameAndSurname(player.getName(), player.getSurname());
    }

    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }
}
