import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {
    @Test
    void makeGuessShouldReturnLowIfBelowTarget() {
        HumanGuessesGame game = new HumanGuessesGame(400);

        assertEquals(GuessResult.LOW, game.makeGuess(399));
        assertEquals(GuessResult.LOW, game.makeGuess(200));
    }

    @Test
    void makeGuessShouldReturnHightIfAboveTarget() {
        HumanGuessesGame game = new HumanGuessesGame(200);

        assertEquals(GuessResult.HIGH, game.makeGuess(201));
        assertEquals(GuessResult.HIGH, game.makeGuess(500));
    }

    @Test
    void makeGuessShouldReturnCorrectIfEqual() {
        // Test all possible target values
       for (int i = 0; i <= 1000; i++) {
           HumanGuessesGame game = new HumanGuessesGame(i);

           assertEquals(GuessResult.CORRECT, game.makeGuess(i));
       }
    }

    @Test
    void isDoneShouldBeTrueAfterCorrectGuess() {
        HumanGuessesGame game = new HumanGuessesGame(100);
        game.makeGuess(100);

        assertTrue(game.isDone());
    }

    @Test
    void numGuessesShouldBeCorrect() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        for (int i = 0; i < 100; i++) {
            assertEquals(i, game.getNumGuesses());
            game.makeGuess(400);
        }
    }

    @Test
    void numGuessesShouldBeCorrectAfterEnd() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        for (int i = 0; i < 23; i++) game.makeGuess(400);
        game.makeGuess(500);

        assertEquals(24, game.getNumGuesses());
    }
}
