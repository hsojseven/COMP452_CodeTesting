import org.junit.jupiter.api.Test;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//using dependency injection
public class GameResultTest {
    // test writeFile
    @Test
    void checkWriteNumGuesses() {
        StringWriter writer = new StringWriter();
        CSVwriterDouble CSVwriter = new CSVwriterDouble(writer);
        GameResult res = new GameResult(true, 1, 1);

        res.writeToFile(CSVwriter, LocalDateTime.parse("2022-02-24T20:10:29.110278500"));

        assertEquals(1, Integer.parseInt(CSVwriter.line[1]));
    }

    @Test
    void checkWriteDateTime() {
        StringWriter writer = new StringWriter();
        CSVwriterDouble CSVwriter = new CSVwriterDouble(writer);
        GameResult res = new GameResult(true, 1, 1);

        res.writeToFile(CSVwriter, LocalDateTime.parse("2022-02-24T20:10:29.110278500"));

        assertTrue(CSVwriter.line[0].equals("2022-02-24T20:10:29.110278500"));
    }
}
