import com.opencsv.CSVWriter;
import java.io.Writer;

public class CSVwriterDouble extends CSVWriter {
    String[] line;

    public CSVwriterDouble(Writer writer) {
        super(writer);
    }

    @Override
    public void writeNext(String[] nextLine) {
        line = nextLine;
    }
}
