package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

import org.springframework.stereotype.Service;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameRequest;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameResponse;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.Game;

@Service
public class GameService {

    private final Game game;

    private final PlayerService playerService;

    public GameService(Game game, PlayerService playerService) {
        this.game = game;
        this.playerService = playerService;
    }

    //TODO transactional
    public GameResponse handleGame(GameRequest gameRequest) throws PlayerException {
        Player player = playerService.findPlayer(gameRequest.getPlayerUsername());
            double amount = game.bet(gameRequest.getBetNumber(), gameRequest.getBetAmount());
            Transaction transaction = new Transaction(amount);
            playerService.addPlayerTransaction(player, transaction);
            return new GameResponse(player, transaction);

    }


}
