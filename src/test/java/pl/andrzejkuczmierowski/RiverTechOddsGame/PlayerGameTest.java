package pl.andrzejkuczmierowski.RiverTechOddsGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.Game;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.NumberGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class PlayerGameTest {

    NumberGenerator numberGenerator;
    Game game;

    @BeforeEach
    public void init() {
        numberGenerator = Mockito.mock(NumberGenerator.class);
        game = new Game(numberGenerator);
    }

    @Test
    public void gameBetTest() {
        //when
        Mockito.when(numberGenerator.generate()).thenReturn(5);
        //then
        assertEquals(600, game.bet(4, 120));
        assertEquals(-120, game.bet(1, 120));
        assertEquals(1200, game.bet(5, 120));
        assertEquals(60, game.bet(3, 120));

    }
}
