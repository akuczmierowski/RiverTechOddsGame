package pl.andrzejkuczmierowski.RiverTechOddsGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.NumberGenerator;
import pl.andrzejkuczmierowski.RiverTechOddsGame.utils.NumberGeneratorImpl;

@SpringBootApplication
public class RiverTechOddsGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiverTechOddsGameApplication.class, args);
    }

    @Bean
    public NumberGenerator numberGenerator() {
        return new NumberGeneratorImpl();
    }
}
