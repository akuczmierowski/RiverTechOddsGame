package pl.andrzejkuczmierowski.RiverTechOddsGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.andrzejkuczmierowski.RiverTechOddsGame.dto.DTOMapper;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameRequest;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameResponse;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.BetRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.TransactionRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.GameService;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerService;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.Game;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.NumberGenerator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class GameServiceTest {

    NumberGenerator numberGenerator;
    PlayerRepository playerRepository;
    TransactionRepository transactionRepository;
    BetRepository betRepository;
    DTOMapper dtoMapper;
    PlayerService playerService;
    Game game;
    GameService gameService;

    GameResponse gameResponse;

    @BeforeEach
    public void init() {
        numberGenerator = Mockito.mock(NumberGenerator.class);
        playerRepository = Mockito.mock(PlayerRepository.class);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        betRepository=Mockito.mock(BetRepository.class);
        dtoMapper=Mockito.mock(DTOMapper.class);
        playerService = new PlayerService(playerRepository, transactionRepository,dtoMapper);
        game = new Game(numberGenerator);
        gameService = new GameService(game, playerService, betRepository);
    }

    @Test
    public void handleGameTest() {
        String testPlayer = "testuser";
        GameRequest gameRequest = new GameRequest(10, 300, testPlayer);
        Player player = new Player("John", "Smith", testPlayer, 1200.0);
        Mockito.when(playerRepository.findByUsername(testPlayer)).thenReturn(Optional.of(player));
        //win case
        Mockito.when(numberGenerator.generate()).thenReturn(4);
        gameResponse = gameService.handleGame(gameRequest);
        assertEquals(900, gameResponse.getPlayer().getBalance());
        //lose case
        gameRequest = new GameRequest(3, 300, testPlayer);
        gameResponse = gameService.handleGame(gameRequest);
        assertEquals(2400, gameResponse.getPlayer().getBalance());

    }
}
