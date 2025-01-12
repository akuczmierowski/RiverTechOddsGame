package pl.andrzejkuczmierowski.RiverTechOddsGame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    //TODO check cascade type
    @ManyToOne(cascade= {CascadeType.ALL})
    @JoinColumn(name = "player_id", nullable = false)
    @JsonIgnore
    @Setter
    private Player player;

    public Transaction() {
    }

    public Transaction(Long id, TransactionType type, BigDecimal amount, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public enum TransactionType {
        WIN,
        LOSE
    }
}
