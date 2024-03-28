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

public class CSVFileReader implements FileReader {

    @Override
    public List<String> readFile(String filePath) throws IllegalArgumentException {
        List<String> lines = new ArrayList<>();

        try {
            InputStream inputStream = CSVFileReader.class.getResourceAsStream(filePath);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                CSVReader csvFileReader = new CSVReader(inputStreamReader);
                String[] headers = csvFileReader.readNext();
                if (headers != null) {
                    lines.add(String.join(",", headers));
                }
                String[] nextLine;
                while ((nextLine = csvFileReader.readNext()) != null) {
                    if (nextLine.length != headers.length) {
                        throw new IllegalArgumentException("Number of values in the row does not match the number of columns.");
                    }
                    lines.add(String.join(",", nextLine));
                }
                csvFileReader.close();
            } else {
                throw new IllegalArgumentException("The file you provided does not exist.");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * reads a .csv file and saves the values in a table
     *
     * @param file the file path
     * @return a table filled with csv data - represented as String values
     * @throws IllegalArgumentException
     */
    public Table read(String file) throws IllegalArgumentException{

        List<String> fileContent = readFile(file);

        Table table = new Table();
        if (fileContent.isEmpty()) {
            return table;
        }

        String[] headers = fileContent.get(0).split(",");
        List<Column> columns = new ArrayList<>();
        for (String header : headers) {
            columns.add(new Column(header, new ArrayList<>()));
        }

        IntStream.range(1, fileContent.size()).forEach(i -> {
            String[] values = fileContent.get(i).split(",");
            if (values.length != headers.length) {
                throw new IllegalArgumentException("Number of values in the row does not match the number of columns.");
            }
            IntStream.range(0, headers.length).forEach(j -> columns.get(j).addValue(values[j]));
        });

        table = new Table(columns);
        return table;
    }


}
