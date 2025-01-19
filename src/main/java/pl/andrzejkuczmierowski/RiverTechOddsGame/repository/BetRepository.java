package pl.andrzejkuczmierowski.RiverTechOddsGame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
}
