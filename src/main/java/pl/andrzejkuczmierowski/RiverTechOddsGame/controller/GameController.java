package pl.andrzejkuczmierowski.RiverTechOddsGame.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameRequest;
import pl.andrzejkuczmierowski.RiverTechOddsGame.model.GameResponse;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.GameService;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerException;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "/play")
    public ResponseEntity<GameResponse> play(@RequestBody GameRequest gameRequest) {

        GameResponse gameResponse = gameService.handleGame(gameRequest);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<String> handleDuplicateUsername(PlayerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
