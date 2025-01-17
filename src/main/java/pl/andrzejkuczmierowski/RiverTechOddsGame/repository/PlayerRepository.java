package pl.andrzejkuczmierowski.RiverTechOddsGame.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.dto.PlayerDTO;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PagingAndSortingRepository<Player,Long> {

    Optional<Player> findByUsername(String username);

    Page<Player> findAll(Pageable pageable);

    @Override
    <S extends Player> S save(S entity);

    @Query("SELECT new pl.andrzejkuczmierowski.RiverTechOddsGame.dto.PlayerDTO(p.username, (p.balance - 1000)) " +
            "FROM Player p " +
            "WHERE p.balance > 0 " +
            "AND p.balance != 1000 " +
            "ORDER BY p.balance DESC")
    List<PlayerDTO> topPlayers();
}
