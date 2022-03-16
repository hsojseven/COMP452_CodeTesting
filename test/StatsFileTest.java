import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class StatsFileTest {
    // test numGames
    @Test
    void zeroGames() {
        SortedMap<Integer, Integer>  statsMap = new TreeMap<>();
        StatsFile stat = new StatsFile(statsMap);

        assertEquals(0, stat.numGames(1));
    }

}
