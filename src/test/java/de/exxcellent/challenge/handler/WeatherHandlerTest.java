package de.exxcellent.challenge.handler;

import de.exxcellent.challenge.table.Column;
import de.exxcellent.challenge.table.Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A class testing the handler for the weather data (de/exxcellent/challenge/handler/WeatherHandler.java).
 *
 * @author Magdalena von Schwerin
 */
public class WeatherHandlerTest {

    @Test
    public void testGetDayWithSmallestTemperatureSpread() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("1", "2", "3")));
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("9", "10", "27")));
        Table weatherData = new Table(columns);

        WeatherHandler weatherHandler = new WeatherHandler(weatherData);
        String result = weatherHandler.getDayWithSmallestTemperatureSpread();

        assertEquals("1", result);
    }

    /**
     * Note: The task did not define what should happen if there are multiple days with the smallest temperature spread.
     * Here, the day with the highest number is chosen.
     * Implementation could be adapted to return a List of days.
     */
    @Test
    public void testGetDayWithSmallestTemperatureSpreadMultiple() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("1", "2", "3")));
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("5", "15", "25")));
        Table weatherData = new Table(columns);

        WeatherHandler weatherHandler = new WeatherHandler(weatherData);
        String result = weatherHandler.getDayWithSmallestTemperatureSpread();

        assertEquals("3", result);
    }


    @Test
    public void testGetDayWithSmallestTemperatureSpreadWeatherDataNull() {
        WeatherHandler weatherHandler = new WeatherHandler(null);
        assertThrows(NullPointerException.class, weatherHandler::getDayWithSmallestTemperatureSpread);
    }

    @Test
    public void testGetDayWithSmallestTemperatureSpreadEmptyTable() {
        Table weatherData = new Table();
        WeatherHandler weatherHandler = new WeatherHandler(weatherData);
        assertThrows(IllegalStateException.class, weatherHandler::getDayWithSmallestTemperatureSpread);
    }

    /**
     * Test for IllegalStateException when a required column is missing
     */
    @Test
    public void testGetDayWithSmallestTemperatureSpreadMissingColumn() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("Monday", "Tuesday", "Wednesday")));
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        Table weatherData = new Table(columns);
        WeatherHandler weatherHandler = new WeatherHandler(weatherData);

        assertThrows(IllegalStateException.class, weatherHandler::getDayWithSmallestTemperatureSpread);
    }

    /**
     * Test for IllegalStateException when data cannot be parsed as Integers
     */
    @Test
    public void testGetDayWithSmallestTemperatureSpreadInvalidDataFormat() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Day", List.of("Monday", "Tuesday", "Wednesday")));
        columns.add(new Column("MxT", List.of("10", "20", "30")));
        columns.add(new Column("MnT", List.of("5", "15", "25a")));
        Table weatherData = new Table(columns);
        WeatherHandler weatherHandler = new WeatherHandler(weatherData);

        assertThrows(IllegalStateException.class, weatherHandler::getDayWithSmallestTemperatureSpread);
    }

}
