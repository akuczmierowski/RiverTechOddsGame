package pl.andrzejkuczmierowski.RiverTechOddsGame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
@AllArgsConstructor
@Getter
public class GameResponse {
    private Player player;
    private Transaction transaction;
}
