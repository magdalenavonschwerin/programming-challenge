package de.exxcellent.challenge.handler;

import de.exxcellent.challenge.table.Column;
import de.exxcellent.challenge.table.Table;

import java.util.List;
import java.util.stream.IntStream;

/**
 * A class that represents a handler for data in table format.
 * The values in the table are of String format.
 *
 * @author Magdalena von Schwerin
 */
public class TableDataHandler implements DataHandler{
    private final Table data;

    private final List<String> columnHeaders;

    public TableDataHandler(Table data, List<String> columnHeaders) {

        this.data = data;
        this.columnHeaders = columnHeaders;

    }

    /**
     * Determines the value of the desired column with the smallest distance
     * between column 1 and column 2.
     *
     * For calculating the distance tha values must be castable to Integer.
     *
     * @return the value corresponding to the smallest distance
     * @throws IllegalStateException
     */
    @Override
    public String process() throws IllegalStateException, NullPointerException, IllegalArgumentException {
        if (data == null || columnHeaders == null) {
            throw new NullPointerException("Table data or headers is null");
        }
        if (columnHeaders.size() != 3){
            throw new IllegalArgumentException("Exactly three column headers are necessary for data processing");
        }

        try {
            Column mxt = data.getColumnByHeader(columnHeaders.get(0));
            Column mnt = data.getColumnByHeader(columnHeaders.get(1));
            Column days = data.getColumnByHeader(columnHeaders.get(2));

            List<String> col1Values = mxt.getValues();
            List<String> col2Values = mnt.getValues();

            int smallestDistanceIndex = getIndexWithSmallestDistance(col1Values, col2Values);

            return days.getValueAtIndex(smallestDistanceIndex);

        } catch (NullPointerException e) {
            throw new IllegalStateException("Missing column in data", e);
        }
    }

    /**
     * Determines the column index of the smallest distance between the specified columns 1 and 2.
     *
     * For calculating the distance tha values must be castable to Integer.
     *
     * @param col1Values the values of the first column
     * @param col2Values the values of the second column
     * @return the column index of the smallest distance
     */
    @Override
    public int getIndexWithSmallestDistance(List<String> col1Values, List<String> col2Values){
        try {
            // Convert values to integers
            List<Integer> col1ValueNumbers = col1Values.stream()
                    .map(Integer::valueOf)
                    .toList();
            List<Integer> col2ValueNumbers = col2Values.stream()
                    .map(Integer::valueOf)
                    .toList();

            // get smallest temperature distance
            return IntStream.range(0, col1ValueNumbers.size())
                    .reduce((a, b) -> Math.abs(col1ValueNumbers.get(a) - col2ValueNumbers.get(a)) <
                            Math.abs(col1ValueNumbers.get(b) - col2ValueNumbers.get(b)) ? a : b)
                    .orElse(-1);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Invalid data format in data table", e);
        }
    }
}
