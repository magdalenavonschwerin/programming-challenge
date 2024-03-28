package de.exxcellent.challenge.table;

import java.util.List;

/**
 * A class representing a Column of a table containing String values.
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */
public class Column {

    private String header;

    private List<String> values;

    public Column(String header, List<String> values){
        this.header = header;
        this.values = values;
    }

    public String getValueAtIndex(int index){
        return values.get(index);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void addValue(String value){
        this.values.add(value);
    }

    public void addValueAtIndex(String value, int index){
        if (index >= 0 && index < values.size()) {
            values.add(index, value);
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    public int getSize() {
        return values.size();
    }

    public void removeValueAtIndex(int index) {
        if (index >= 0 && index < values.size()) {
            values.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

}
