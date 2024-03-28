package de.exxcellent.challenge;

import de.exxcellent.challenge.handler.TableDataHandler;
import de.exxcellent.challenge.reader.CSVFileReader;
import de.exxcellent.challenge.table.Table;

import java.util.Arrays;
import java.util.List;
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

            CSVFileReader CSVFileReader = new CSVFileReader();
            String resourcePath = "/de/exxcellent/challenge/" + fileName;


            if(args[0].equals("--weather")){
                Table weatherData = CSVFileReader.read(resourcePath);
                List<String> header = Arrays.asList("MxT", "MnT", "Day");
                TableDataHandler weatherHandler = new TableDataHandler(weatherData, header);
                String dayWithSmallestTempSpread = weatherHandler.process();

                System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

            } else if (args[0].equals("--football")) {
                Table footballData = CSVFileReader.read(resourcePath);
                List<String> header = Arrays.asList("Goals", "Goals Allowed", "Team");
                TableDataHandler footballHandler = new TableDataHandler(footballData, header);
                String teamWithSmallestGoalSpread = footballHandler.process();

                System.out.printf("Team with smallest goal spread : %s%n", teamWithSmallestGoalSpread);
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

