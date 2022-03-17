import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class GameStatsTest {
    final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};

    // test getGuessList
    @Test
    void emptyStats() { // tests that
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        GameStats stat = new StatsFile(statsMap);

        // loop through all bins and ensure a default of 0
        for (String i : stat.getGuessList(BIN_EDGES)) {
            if (0 != Integer.parseInt(i)) { fail(); }
        }

        // returns 0 to pass the test
    }

    @Test
    void binsCountedCorrectly() {
        SortedMap<Integer, Integer> statsMap = new TreeMap<>();
        statsMap.put(1,1);
        statsMap.put(2,1);
        statsMap.put(4,1);
        statsMap.put(6,1);
        statsMap.put(8,1);
        statsMap.put(10,1);
        statsMap.put(12,1);
        statsMap.put(14,1);

        GameStats stat = new StatsFile(statsMap);

        // loop through all bins and ensure only 1 game was counted per bin
        for (String i : stat.getGuessList(BIN_EDGES)) {
            if (1 != Integer.parseInt(i)) { fail(); } // fail if a bin did not have a value of 1
        }

        // returns 0 to pass the test
    }

}
