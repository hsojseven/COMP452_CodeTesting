import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerGuessesGameTest {
    private ComputerGuessesGame game;

    @BeforeEach
    void initTest() {
        game = new ComputerGuessesGame();
    }

    @Test
    void firstGuessShouldBe501() {
        assertEquals(501, game.getLastGuess());
    }

    @Test
    void numGuessesShouldStartAtOne() {
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void numGuessesShouldIncrementWithGuess() {
        int before = game.getNumGuesses();
        game.nextGuess(GuessResult.LOW);
        assertEquals(before + 1, game.getNumGuesses());

        for(int i = 2; i < 20; i++) {
            game.nextGuess(GuessResult.LOW);
            assertEquals(before + i, game.getNumGuesses());
        }
    }

    @Test
    void nextGuessShouldBeHigherIfResultHigh() {
        int lastGuess = game.getLastGuess();
        assertTrue(lastGuess < game.nextGuess(GuessResult.HIGH));
    }

    @Test
    void nextGuessShouldBeLowerIfResultLow() {
        int lastGuess = game.getLastGuess();
        assertTrue(lastGuess > game.nextGuess(GuessResult.LOW));
    }

    @Test
    void gameShouldBeDoneIfNextGuessCalledWithCorrect() {
        assertFalse(game.isDone());
        game.nextGuess(GuessResult.CORRECT);
        assertTrue(game.isDone());
    }
}
