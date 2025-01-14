package pl.andrzejkuczmierowski.RiverTechOddsGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.TransactionRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerException;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerService;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.Game;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.NumberGenerator;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class PlayerGameTest {

    NumberGenerator numberGenerator;
    PlayerRepository playerRepository;
    TransactionRepository transactionRepository;
    PlayerService playerService;
    Game game;

    @BeforeEach
    public void init() {
        numberGenerator = Mockito.mock(NumberGenerator.class);
        playerRepository = Mockito.mock(PlayerRepository.class);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        playerService = new PlayerService(playerRepository, transactionRepository);
        game = new Game(numberGenerator);
    }


    @Test
    //TODO add more cases test
    public void addTransactionTest() throws PlayerException {
        String testPlayer = "testuser";
        Player player = new Player("John", "Smith", testPlayer, 1200.0);
        Mockito.when(playerRepository.findByUsername(testPlayer)).thenReturn(Optional.of(player));
        //win case
        Transaction transaction = new Transaction(200.0);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        assertEquals(transaction, playerService.addPlayerTransaction(player, transaction));
        assertEquals(1400.0, player.getBalance());

    }
}
