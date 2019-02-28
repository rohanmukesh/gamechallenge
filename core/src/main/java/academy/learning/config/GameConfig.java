package academy.learning.config;

import academy.learning.GuessCount;
import academy.learning.MaxNumber;
import academy.learning.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:config/game.properties"})
@ComponentScan(basePackages = "academy.learning")
public class GameConfig {
    // == fields ==

    @Value("${game.maxNumber:20}")
    private int maxNumber;

    @Value("${game.guessCount:100}")
    private int guessCount;

    @Value("${game.minNumber:1}")
    private int minNumber;

    @Bean
    @MinNumber
    public int minNumber() {
        return  minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount1() {
        return guessCount;
    }

    @Bean
    @MaxNumber
    public int maxNumber1(){
        return maxNumber;
    }
}
