package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class testing the data reader that can read csv data and save it in a table (de/exxcellent/challenge/reader/Reader.java).
 *
 * @author Magdalena von Schwerin
 */
public class CSVFileReaderTest {

    private CSVFileReader CSVFileReader;

    @BeforeEach
    public void setUp() {
        CSVFileReader = new CSVFileReader();
    }

    @Test
    public void testReadValidFile() {
        Table table = CSVFileReader.read("/valid_file.csv");
        assertNotNull(table);
        assertEquals(3, table.getNumberOfColumns());
        assertEquals("Header1", table.getColumnByIndex(0).getHeader());
        assertEquals("Header2", table.getColumnByIndex(1).getHeader());
        assertEquals("Header3", table.getColumnByIndex(2).getHeader());
        assertEquals(2, table.getNumberOfRows());
        assertEquals("Value1", table.getValueAt(0, 0));
        assertEquals("Value2", table.getValueAt(0, 1));
        assertEquals("Value3", table.getValueAt(0, 2));
        assertEquals("Value4", table.getValueAt(1, 0));
        assertEquals("Value5", table.getValueAt(1, 1));
        assertEquals("Value6", table.getValueAt(1, 2));
    }

    @Test
    void testReadFileNonexistent() {
        CSVFileReader reader = new CSVFileReader();
        assertThrows(IllegalArgumentException.class, () -> reader.readFile("/nonexistent_file.csv"));
    }

    @Test
    void testReadEmptyFile() {
        CSVFileReader reader = new CSVFileReader();
        List<String> lines = reader.readFile("/empty_file.csv");

        assertNotNull(lines);
        assertTrue(lines.isEmpty());
    }

    @Test
    void testReadFileWithInvalidContent() {
        CSVFileReader reader = new CSVFileReader();
        assertThrows(IllegalArgumentException.class, () -> reader.readFile("/invalid_file.csv"));
    }

}
