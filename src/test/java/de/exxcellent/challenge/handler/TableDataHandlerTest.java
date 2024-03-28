package de.exxcellent.challenge.handler;

import de.exxcellent.challenge.table.Column;
import de.exxcellent.challenge.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A class testing the handler for the weather and football data (de/exxcellent/challenge/handler/TableDataHandler.java).
 *
 * @author Magdalena von Schwerin
 */
public class TableDataHandlerTest {

    private List<String> header;

    private List<Column> columns;
    @BeforeEach
    public void setup(){
        header = Arrays.asList("MxT", "MnT", "Day");
        columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("1", "2", "3")));
    }
    @Test
    public void testGetSmallestDistance() {
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("9", "10", "27")));
        Table weatherData = new Table(columns);

        TableDataHandler weatherHandler = new TableDataHandler(weatherData, header);
        String result = weatherHandler.process();

        assertEquals("1", result);
    }

    /**
     * Note: The task did not define what should happen if there are multiple days with the smallest temperature spread.
     * Here, the day with the highest number is chosen.
     * Implementation could be adapted to return a List of days.
     */
    @Test
    public void testGetSmallestDistanceMultiple() {
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("5", "15", "25")));
        Table weatherData = new Table(columns);

        TableDataHandler weatherHandler = new TableDataHandler(weatherData, header);
        String result = weatherHandler.process();

        assertEquals("3", result);
    }


    @Test
    public void testProcessDataNull() {
        TableDataHandler weatherHandler = new TableDataHandler(null, header);
        assertThrows(NullPointerException.class, weatherHandler::process);
    }

    @Test
    public void testHeadersNull() {
        Table weatherData = new Table(columns);
        TableDataHandler weatherHandler = new TableDataHandler(weatherData, null);
        assertThrows(NullPointerException.class, weatherHandler::process);
    }

    @Test
    public void testProcessEmptyTable() {
        Table weatherData = new Table();
        TableDataHandler weatherHandler = new TableDataHandler(weatherData, header);
        assertThrows(IllegalStateException.class, weatherHandler::process);
    }

    @Test
    public void testHeadersEmpty() {
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("5", "16", "29")));
        Table weatherData = new Table(columns);

        List<String> emptyHeaders= new ArrayList<>();
        TableDataHandler weatherHandler = new TableDataHandler(weatherData, emptyHeaders);
        assertThrows(IllegalArgumentException.class, weatherHandler::process);
    }

    /**
     * Test for IllegalStateException when a required column is missing
     */
    @Test
    public void testProcessMissingColumn() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("Monday", "Tuesday", "Wednesday")));
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        Table weatherData = new Table(columns);
        TableDataHandler weatherHandler = new TableDataHandler(weatherData, header);

        assertThrows(IllegalStateException.class, weatherHandler::process);
    }

    /**
     * Test for IllegalStateException when data cannot be parsed as Integers
     */
    @Test
    public void testProcessInvalidDataFormat() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("Monday", "Tuesday", "Wednesday")));
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("5", "15", "25a")));
        Table weatherData = new Table(columns);
        TableDataHandler weatherHandler = new TableDataHandler(weatherData, header);

        assertThrows(IllegalStateException.class, weatherHandler::process);
    }
}
