package de.exxcellent.challenge.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import de.exxcellent.challenge.table.Column;
import de.exxcellent.challenge.table.Table;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A class representing a reader that reads a CSV file into a table.
 * @author Magdalena von Schwerin
 */

public class Reader {

    /**
     * reads a .csv file and saves the values in a table
     *
     * @param fileName the name of the file in the resources folder
     * @return a table filled with csv data - represented as String values
     * @throws IllegalArgumentException
     */
    public Table read(String fileName) throws IllegalArgumentException {
        Table table = null;
        List<Column> columns = new ArrayList<>();
        InputStream inputStream;

        try {
            inputStream = Reader.class.getResourceAsStream(fileName);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                CSVReader csvReader = new CSVReader(inputStreamReader);
                String[] headers = csvReader.readNext();
                if (headers != null) {
                    for (String header : headers) {
                        columns.add(new Column(header, new ArrayList<>()));
                    }
                }
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null) {
                    if (nextLine.length != columns.size()) {
                        throw new IllegalArgumentException("Number of values in the row does not match the number of columns.");
                    }
                    String[] finalNextLine = nextLine;
                    IntStream.range(0, nextLine.length)
                            .forEach(i -> columns.get(i).addValue(finalNextLine[i]));
                }
                csvReader.close();
                table = new Table(columns);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return table;
    }
}
