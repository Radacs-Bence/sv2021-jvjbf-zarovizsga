package org.training360.finalexam.players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training360.finalexam.teams.Team;

public interface PlayersRepository extends JpaRepository<Player, Long> {
}
