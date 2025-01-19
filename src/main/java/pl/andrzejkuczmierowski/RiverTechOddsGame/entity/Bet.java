package pl.andrzejkuczmierowski.RiverTechOddsGame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "bet_amount", nullable = false)
    private Double betAmount;

    @Column(name = "bet_number", nullable = false)
    private int betNumber;

    @Column(name = "generated_number", nullable = false)
    private int generatedNumber;

    @Column(name = "result", nullable = false)
    private Double result;

    public Bet(Player player, Double betAmount, int betNumber, int generatedNumber, Double result) {
        this.player = player;
        this.betAmount = betAmount;
        this.betNumber = betNumber;
        this.generatedNumber = generatedNumber;
        this.result = result;
    }
}
