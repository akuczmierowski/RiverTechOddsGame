package pl.andrzejkuczmierowski.RiverTechOddsGame.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerException;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Player> registerPlayer(@RequestBody Player player) throws PlayerException {
        playerService.addPlayer(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    @PutMapping(path = "/transaction")
    public ResponseEntity<Transaction>addTranaction(@RequestParam String username, @RequestBody Transaction transaction) throws PlayerException {
        Transaction transaction1 = playerService.addPlayerTransaction(username,transaction);
        return new ResponseEntity<>(transaction1,HttpStatus.OK);
    }

   /* @GetMapping()
    public Player getPlayer(@RequestParam("username") String username){
        return playerRepository.findByUsername(username).get();
    }*/

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<String> handleDuplicateUsername(PlayerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
