package pl.andrzejkuczmierowski.RiverTechOddsGame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private TransactionType type;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    //TODO check cascade type
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @JsonIgnore
    @Setter
    private Player player;

    public Transaction() {
    }

    public Transaction(Double amount) {
        this.type = amount < 0 ? TransactionType.LOSE : TransactionType.WIN;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public enum TransactionType {
        WIN,
        LOSE
    }
}
