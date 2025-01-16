package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TransactionRepository transactionRepository;

    public PlayerService(PlayerRepository playerRepository, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
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

    public Player findPlayer(String username) throws PlayerException {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new PlayerException(String.format("Cannot find player: %s", username)));
    }

    public Page<Player> findAll(PageRequest pageRequest){
        return playerRepository.findAll(pageRequest);
    }

    private Player calculateBalance(Player player, Transaction transaction) {
        Transaction.TransactionType type = transaction.getType();
        switch (type) {
            case WIN -> player.setBalance(player.getBalance() + transaction.getAmount());
            case LOSE -> player.setBalance(player.getBalance() - transaction.getAmount());
        }
        return player;
    }

    public boolean canMakeTransaction(Player player, Double amount) {
        return player.getBalance() - (amount) > 0;
    }
}
