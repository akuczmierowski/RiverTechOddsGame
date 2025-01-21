package pl.andrzejkuczmierowski.RiverTechOddsGame.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.andrzejkuczmierowski.RiverTechOddsGame.dto.DTOMapper;
import pl.andrzejkuczmierowski.RiverTechOddsGame.dto.PlayerDTO;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
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
    public ResponseEntity<Page<PlayerDTO>> findAll(@RequestParam("pageNumber") int pageNumber,
                                                   @RequestParam("playersPerPage") int playersPerPage) {
        Page<PlayerDTO> players = playerService.findAll(PageRequest.of(pageNumber, playersPerPage));
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/best")
    public List<PlayerDTO> best(@RequestParam("numberOfPlayers") int numberOfPlayers) {
        return playerService.findBestPlayers(numberOfPlayers);
    }

}
