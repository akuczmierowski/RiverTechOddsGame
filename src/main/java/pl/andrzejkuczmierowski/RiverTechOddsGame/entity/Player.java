package pl.andrzejkuczmierowski.RiverTechOddsGame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public Player(String name, String surname, String username, Double balance) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.balance = balance;
    }

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter
    @JsonIgnore
    private List<Transaction> transactions;
}
