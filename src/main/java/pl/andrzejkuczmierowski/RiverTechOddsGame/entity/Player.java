package pl.andrzejkuczmierowski.RiverTechOddsGame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    @Setter
    private Double balance;

    public Player() {
    }

    public Player(Long id, String name, String surname, String username, Double balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.balance = balance;
    }

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Setter
    private List<Transaction> transactions;
}
