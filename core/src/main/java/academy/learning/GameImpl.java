package academy.learning;

import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
@Getter
public class GameImpl implements Game {

    private final NumberGenerator numberGenerator;

    private final int guessCount;

    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator,@GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    @Setter
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    @PostConstruct
    public void reset() {
        guess = numberGenerator.getMinNumber();
        smallest = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("postconstruct number is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess <number) {
                smallest = guess + 1;
            }
        }
        remainingGuesses-- ;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
