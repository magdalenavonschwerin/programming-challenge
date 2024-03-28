package de.exxcellent.challenge.handler;

import de.exxcellent.challenge.table.Column;
import de.exxcellent.challenge.table.Table;

import java.util.List;
import java.util.stream.IntStream;

/**
 * A handler class that can perform operations on a weather data table.
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */
public class WeatherHandler implements DataHandler {

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
    @Override
    public String process() throws IllegalStateException {
        if (weatherData == null) {
            throw new NullPointerException("Weather data is null");
        }

        try {
            Column mxt = weatherData.getColumnByHeader("MxT");
            Column mnt = weatherData.getColumnByHeader("MnT");
            Column days = weatherData.getColumnByHeader("Day");

            List<String> mxtValues = mxt.getValues();
            List<String> mntValues = mnt.getValues();

            int smallestDistanceIndex = getDayWithSmallestTempDistance(mxtValues, mntValues);

            return days.getValueAtIndex(smallestDistanceIndex);

        } catch (NullPointerException e) {
            throw new IllegalStateException("Missing column in weather data", e);
        }
    }

    private int getDayWithSmallestTempDistance(List<String> mxtValues, List<String> mntValues){
        try {
            // Convert values to integers
            List<Integer> mxtValueNumbers = mxtValues.stream()
                    .map(Integer::valueOf)
                    .toList();
            List<Integer> mntValueNumbers = mntValues.stream()
                    .map(Integer::valueOf)
                    .toList();

            // get smallest temperature distance
            return IntStream.range(0, mxtValueNumbers.size())
                    .reduce((a, b) -> Math.abs(mxtValueNumbers.get(a) - mntValueNumbers.get(a)) <
                            Math.abs(mxtValueNumbers.get(b) - mntValueNumbers.get(b)) ? a : b)
                    .orElse(-1);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Invalid data format in weather data", e);
        }

    }

}
