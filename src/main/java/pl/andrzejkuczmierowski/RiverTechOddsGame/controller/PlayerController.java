package pl.andrzejkuczmierowski.RiverTechOddsGame.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerException;
import pl.andrzejkuczmierowski.RiverTechOddsGame.service.PlayerService;

import java.util.List;

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

    @GetMapping(path = "/all")
    public ResponseEntity<Page<Player>> findAll(@RequestParam("pageNumber") int pageNumber) {
        org.springframework.data.domain.Page<Player> players = playerService.findAll(PageRequest.of(pageNumber, 2));
        return new ResponseEntity<>(players, HttpStatus.OK);
    }


}
