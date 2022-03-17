import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StatsFileTest {
    // test numGames
    @Test
    void zeroGames() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        StatsFile stat = new StatsFile(statsMap);

        assertEquals(0, stat.numGames(1));
    }

    @Test
    void twoGames() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        statsMap.put(1,1);
        statsMap.put(2,5);
        StatsFile stat = new StatsFile(statsMap);

        assertEquals(5, stat.numGames(2));
    }

    @Test
    void testDefault() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        statsMap.put(1,1);
        statsMap.put(2,5);
        StatsFile stat = new StatsFile(statsMap);

        assertEquals(0, stat.numGames(25));
    }

    // test maxNumGueses
    @Test
    void getLastKey() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        statsMap.put(1,1);
        statsMap.put(2,5);
        StatsFile stat = new StatsFile(statsMap);

        assertEquals(2, stat.maxNumGuesses());
    }

    @Test
    void testIsEmpty() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        StatsFile stat = new StatsFile(statsMap);

        assertEquals(0, stat.maxNumGuesses());
    }

    // test parseData
    // tests exceptios thrown in parsing the CSV data
    @Test
    void catchNumberFormat() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        StatsFile stat = new StatsFile(statsMap);
        LocalDateTime today = LocalDateTime.now();


        String[] values = {today.toString(), "string"};

        assertThrows(NumberFormatException.class,
                () -> stat.parseData(today.minusDays(30), values));
    }

    @Test
    void catchDateFormat() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        StatsFile stat = new StatsFile(statsMap);
        LocalDateTime today = LocalDateTime.now();

        String[] values = {"aFakeDateTime", "4"};

        assertThrows(DateTimeParseException.class,
                () -> stat.parseData(today, values));
    }
}
