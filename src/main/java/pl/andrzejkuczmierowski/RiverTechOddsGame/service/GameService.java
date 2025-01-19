package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Bet;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameRequest;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameResponse;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.BetRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.Game;

@Service
public class GameService {

    private final Game game;

    private final PlayerService playerService;

    private final BetRepository betRepository;

    public GameService(Game game, PlayerService playerService, BetRepository betRepository) {
        this.game = game;
        this.playerService = playerService;
        this.betRepository = betRepository;
    }


    @Transactional(rollbackFor = PlayerException.class)
    public GameResponse handleGame(GameRequest gameRequest) throws PlayerException {
        double requestAmount = gameRequest.getBetAmount();
        int betNumber = gameRequest.getBetNumber();
        Player player = playerService.findPlayer(gameRequest.getPlayerUsername());
        if (playerService.canMakeTransaction(player, requestAmount)) {
            double result = game.bet(betNumber, requestAmount);
            Bet bet = new Bet(player, requestAmount, betNumber, game.getDrawnNumber(), result);
            Transaction transaction = new Transaction(result);
            playerService.addPlayerTransaction(player, transaction);
            betRepository.save(bet);
            return new GameResponse(player, transaction);
        }
        throw new PlayerException(String.format("Player %s has not enough credits ", player.getUsername()));

    }

}
