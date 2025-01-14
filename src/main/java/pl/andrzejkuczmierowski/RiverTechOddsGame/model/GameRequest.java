package pl.andrzejkuczmierowski.RiverTechOddsGame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//TODO Player should be in app context
public class GameRequest {

    private int betNumber;
    private double betAmount;
    private String playerUsername;
}
