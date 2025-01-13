package pl.andrzejkuczmierowski.RiverTechOddsGame.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.PlayerRepository;
import pl.andrzejkuczmierowski.RiverTechOddsGame.repository.TransactionRepository;

import java.math.BigDecimal;
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

    public Transaction addPlayerTransaction(String username, Transaction transaction) throws PlayerException {
        Optional<Player> playerOptional = playerRepository.findByUsername(username);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            if (canMakeTransaction(player, transaction.getAmount())) {
                calculateBalance(player, transaction);
                transaction.setPlayer(player);
                return transactionRepository.save(transaction);
            }
            throw new PlayerException(String.format("Player: %s has not enough credits", username));
        }
        String message = String.format("Player for username: %s does not exist in database!", username);
        throw new PlayerException(message);
    }

    private Player calculateBalance(Player player, Transaction transaction) {
        Transaction.TransactionType type = transaction.getType();
        switch (type) {
            case WIN -> {
                Double balance = player.getBalance();
                player.setBalance(balance+transaction.getAmount());
                return player;
            }
            case LOSE -> {
                Double balance = player.getBalance();
                player.setBalance(balance-transaction.getAmount());
                return player;
            }
        }
        return player;
    }

    private boolean canMakeTransaction(Player player, Double amount) {
        return player.getBalance()-(amount) > 0;
    }
}
