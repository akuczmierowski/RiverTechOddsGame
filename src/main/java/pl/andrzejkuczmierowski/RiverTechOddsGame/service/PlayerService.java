package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.andrzejkuczmierowski.RiverTechOddsGame.dto.DTOMapper;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.dto.PlayerDTO;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TransactionRepository transactionRepository;

    private final DTOMapper dtoMapper;

    public PlayerService(PlayerRepository playerRepository, TransactionRepository transactionRepository, DTOMapper dtoMapper) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
        this.dtoMapper = dtoMapper;
    }

    public Player addPlayer(Player player) throws PlayerException {
        try {
            return playerRepository.save(player);
        } catch (DataIntegrityViolationException e) {
            String message = String.format("Failed to add player with username '%s': user already exists.",
                    player.getUsername());
            log.error(message);
            throw new PlayerException(message, e);
        }
    }

    @Transactional
    public Transaction addPlayerTransaction(Player player, Transaction transaction) {
        calculateBalance(player, transaction);
        transaction.setPlayer(player);
        return transactionRepository.save(transaction);

    }
    //TODO should subtract 1000 credits as this is default amount?
    public List<PlayerDTO> findBestPlayers(int numberOfPlayers) {
        return playerRepository.topPlayers(numberOfPlayers)
                .stream()
                .map(dtoMapper::playerToPlayerDTO)
                .toList();

    }

    public Player findPlayer(String username) throws PlayerException {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new PlayerException(String.format("Cannot find player: %s", username)));
    }

    public Page<PlayerDTO> findAll(PageRequest pageRequest) {
        return playerRepository.findAll(pageRequest).map(p->dtoMapper.playerToPlayerDTO(p));
    }

    private Player calculateBalance(Player player, Transaction transaction) {
        player.setBalance(player.getBalance() + transaction.getAmount());
        return player;
    }

    public boolean canMakeTransaction(Player player, Double amount) {
        return player.getBalance() - (amount) > 0;
    }
}
