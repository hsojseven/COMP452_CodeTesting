import com.opencsv.CSVWriter;
import java.time.LocalDateTime;

/**
 * Data class to hold the result of a game
 * NOTE: You can refactor and edit this file if needed
 */
public class GameResult {
    public final boolean humanWasPlaying;
    public final int correctValue;
    public final int numGuesses;

    public GameResult(boolean humanWasPlaying, int correctValue, int numGuesses){
        this.humanWasPlaying = humanWasPlaying;
        this.correctValue = correctValue;
        this.numGuesses = numGuesses;
    }

    public void writeToFile(CSVWriter writer, LocalDateTime time) {
        String [] record = new String[2];
        record[0] = time.toString();
        record[1] = Integer.toString(numGuesses);

        writer.writeNext(record);
    }
}
