package de.exxcellent.challenge;

import de.exxcellent.challenge.handler.WeatherHandler;
import de.exxcellent.challenge.reader.Reader;
import de.exxcellent.challenge.table.Table;

import java.util.regex.Pattern;

/**
 * The Entry for the exxcellent solutions programming weatherdata challenge.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */
public final class App {

    /**
     * This is the main entry method of the data processing program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

            if(args.length != 2){
                throw new IllegalArgumentException("Usage: java App (--weather|--football) <data_csv_file>");
            } else if(!isValidChallenge(args[0]) || !isValidCSV(args[1])){
                throw new IllegalArgumentException("Usage: java App (--weather|--football) <data_csv_file>");
            }

            String fileName = args[1];

            Reader reader = new Reader();
            String resourcePath = "/de/exxcellent/challenge/" + fileName;


            if(args[0].equals("--weather")){
                Table weatherData = reader.read(resourcePath);
                WeatherHandler weatherHandler = new WeatherHandler(weatherData);
                String dayWithSmallestTempSpread = weatherHandler.getDayWithSmallestTemperatureSpread();

                System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
            } else if (args[0].equals("--football")) {

                String teamWithSmallestGoalSpread = "A good team";
                System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
            }


    }

    private static boolean isValidCSV(String fileName){
        Pattern CSV_FILE_NAME_PATTERN = Pattern.compile("^[^\\\\/:*?\"<>|]+\\.csv$");
        return CSV_FILE_NAME_PATTERN.matcher(fileName).matches();
    }
    private static boolean isValidChallenge(String challenge){
        return challenge.equals("--weather") || challenge.equals("--football");
    }
}

