package de.exxcellent.challenge.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Table class representing a table made up of columns (de/exxcellent/challenge/table/Table.java).
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */
public class TableTest {

    private Table table;

    @BeforeEach
    public void setUp() {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Name", new ArrayList<>()));
        columns.add(new Column("Age", new ArrayList<>()));
        columns.add(new Column("City", new ArrayList<>()));
        table = new Table(columns);
    }

    // Test the methods

    @Test
    public void testGetColumnByIndex() {
        assertEquals("Name", table.getColumnByIndex(0).getHeader());
        assertEquals("Age", table.getColumnByIndex(1).getHeader());
        assertEquals("City", table.getColumnByIndex(2).getHeader());
    }

    @Test
    public void testGetColumnByHeader() {
        assertEquals("Name", table.getColumnByHeader("Name").getHeader());
        assertEquals("Age", table.getColumnByHeader("Age").getHeader());
        assertEquals("City", table.getColumnByHeader("City").getHeader());
        assertNull(table.getColumnByHeader("Gender"));
    }

    @Test
    public void testGetColumnIndexByHeader() {
        assertEquals(0, table.getColumnIndexByHeader("Name"));
        assertEquals(1, table.getColumnIndexByHeader("Age"));
        assertEquals(2, table.getColumnIndexByHeader("City"));
        assertEquals(-1, table.getColumnIndexByHeader("Gender"));
    }

    @Test
    public void testGetValueAt() {
        List<String> newRow = Arrays.asList("John", "30", "New York");
        table.addRow(newRow);
        assertEquals("John", table.getValueAt(0, 0));
        assertEquals("30", table.getValueAt(0, 1));
        assertEquals("New York", table.getValueAt(0, 2));
    }

    @Test
    public void testRemoveColumnByIndex() {
        table.removeColumnByIndex(0);
        assertEquals(2, table.getNumberOfColumns());
        assertEquals("Age", table.getColumnByIndex(0).getHeader());
        assertEquals("City", table.getColumnByIndex(1).getHeader());
    }

    @Test
    public void testRemoveColumnByHeader() {
        table.removeColumnByHeader("Age");
        assertEquals(2, table.getNumberOfColumns());
        assertEquals("Name", table.getColumnByIndex(0).getHeader());
        assertEquals("City", table.getColumnByIndex(1).getHeader());
    }

    @Test
    public void testAddRow() {
        List<String> newRow = Arrays.asList("John", "30", "New York");
        table.addRow(newRow);
        assertEquals("John", table.getValueAt(0, 0));
        assertEquals("30", table.getValueAt(0, 1));
        assertEquals("New York", table.getValueAt(0, 2));
    }

    @Test
    public void testRemoveRow() {
        List<String> newRow1 = Arrays.asList("John", "30", "New York");
        List<String> newRow2 = Arrays.asList("Alice", "25", "Los Angeles");
        table.addRow(newRow1);
        table.addRow(newRow2);

        table.removeRow(0);
        assertEquals("Alice", table.getValueAt(0, 0));
        assertEquals("25", table.getValueAt(0, 1));
        assertEquals("Los Angeles", table.getValueAt(0, 2));
        assertEquals(1, table.getNumberOfRows());
    }

    // Test the exceptions

    @Test
    public void testGetValueAtThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> table.getValueAt(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> table.getValueAt(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> table.getValueAt(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> table.getValueAt(0, 3));
    }

    @Test
    public void testRemoveColumnByIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> table.removeColumnByIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> table.removeColumnByIndex(3));
    }

    @Test
    public void testRemoveColumnByHeaderThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> table.removeColumnByHeader("Salary"));
    }

    @Test
    public void testGetColumnByIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> table.getColumnByIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> table.getColumnByIndex(3));
    }

    @Test
    public void testAddRowThrowsException() {
        List<String> invalidRow = Arrays.asList("John", "30");
        assertThrows(IllegalArgumentException.class, () -> table.addRow(invalidRow));
    }

    @Test
    public void testRemoveRowThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> table.removeRow(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> table.removeRow(1));
    }
}
