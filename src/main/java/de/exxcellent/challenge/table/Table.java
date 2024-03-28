package de.exxcellent.challenge.table;

import java.util.List;

/**
 * A class representing a Table made up of Columns containing String values.
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */
public class Table {
    private List<Column> columns;

    public Table(){}

    public Table(List<Column> columns) {
        this.columns = columns;
    }

    public Column getColumnByIndex(int columnIndex) throws IndexOutOfBoundsException {
        if (columnIndex >= 0 && columnIndex < columns.size()) {
            return columns.get(columnIndex);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Column getColumnByHeader(String header) {
        for (Column column : columns) {
            if (column.getHeader().equals(header)) {
                return column;
            }
        }
        return null;
    }

    public int getColumnIndexByHeader(String header) {
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getHeader().equals(header)) {
                return i;
            }
        }
        return -1;
    }

    public String getValueAt(int rowIndex, int columnIndex) throws IndexOutOfBoundsException {
        if (rowIndex >= 0 && rowIndex < getNumberOfRows() && columnIndex >= 0 && columnIndex < columns.size()){
            return columns.get(columnIndex).getValueAtIndex(rowIndex);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isEmpty() {
        return columns.isEmpty();
    }

    public int getNumberOfColumns() {
        return columns.size();
    }

    public void removeColumnByIndex(int index) throws IndexOutOfBoundsException {
        if(index >= 0 && index < columns.size()){
            columns.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void removeColumnByHeader(String header) throws IllegalArgumentException {
        int index = getColumnIndexByHeader(header);
        if(index >= 0){
            columns.remove(index);
        } else {
            throw new IllegalArgumentException("No column with given header found.");
        }
    }

    /**
     * Add a row to the table
     *
     * @param newRow a list of String values to add to the table
     * @throws IllegalArgumentException row must be the same size as the columns list
     */
    public void addRow(List<String> newRow) throws IllegalArgumentException {
        if (newRow.size() != columns.size()) {
            throw new IllegalArgumentException("Number of values in the row must match the number of columns.");
        }
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).addValue(newRow.get(i));
        }
    }

    /**
     * Remove a single row from the table at index
     * @param rowIndex index for the row to remove
     * @throws IndexOutOfBoundsException
     */
    public void removeRow(int rowIndex) throws IndexOutOfBoundsException {
        if (rowIndex >= 0 && rowIndex < getNumberOfRows()) {
            for (Column column : columns) {
                column.removeValueAtIndex(rowIndex);
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }
    }

    /**
     * Determine the number of rows in the table assuming that all columns have the same size.
     *
     * @return number of rows in table = size of column
     */
    public int getNumberOfRows() {
        if (columns.isEmpty()) {
            return 0;
        }
        return columns.get(0).getSize();
    }


}
