package de.exxcellent.challenge.handler;

import de.exxcellent.challenge.table.Column;
import de.exxcellent.challenge.table.Table;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * A handler class that can perform operations on a weather data table.
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */
public class WeatherHandler {

    private final Table weatherData;

    public WeatherHandler(Table weatherData) {
        this.weatherData = weatherData;
    }

    /**
     * Calculates the smallest temperature distance on a single day (column "MxT" - "MnT")
     * and determines the day with the smallest temperature distance
     *
     * @return number of the day with the smallest temperature distance
     */
    public String getDayWithSmallestTemperatureSpread() throws IllegalStateException {
        if (weatherData == null) {
            throw new NullPointerException("Weather data is null");
        }

        try {
            Column mxt = weatherData.getColumnByHeader("MxT");
            Column mnt = weatherData.getColumnByHeader("MnT");
            Column days = weatherData.getColumnByHeader("Day");

            List<String> mxtValues = mxt.getValues();
            List<String> mntValues = mnt.getValues();

            // Convert values to integers
            List<Integer> mxtValueNumbers = mxtValues.stream()
                    .map(Integer::valueOf)
                    .toList();
            List<Integer> mntValueNumbers = mntValues.stream()
                    .map(Integer::valueOf)
                    .toList();

            // get smallest temperature distance
            int smallestDistanceIndex = IntStream.range(0, mxtValueNumbers.size())
                    .reduce((a, b) -> Math.abs(mxtValueNumbers.get(a) - mntValueNumbers.get(a)) <
                            Math.abs(mxtValueNumbers.get(b) - mntValueNumbers.get(b)) ? a : b)
                    .orElse(-1);

            return days.getValueAtIndex(smallestDistanceIndex);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Missing column in weather data", e);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Invalid data format in weather data", e);
        }
    }
}
