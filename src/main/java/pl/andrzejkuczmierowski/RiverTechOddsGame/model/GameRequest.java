package pl.andrzejkuczmierowski.RiverTechOddsGame.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//TODO Player should be in app context
public class GameRequest {
    @Max(10)
    @Min(1)
    private int betNumber;
    private double betAmount;
    private String playerUsername;
}
