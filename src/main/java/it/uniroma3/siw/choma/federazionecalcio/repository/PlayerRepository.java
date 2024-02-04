package it.uniroma3.siw.choma.federazionecalcio.repository;

import it.uniroma3.siw.choma.federazionecalcio.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
