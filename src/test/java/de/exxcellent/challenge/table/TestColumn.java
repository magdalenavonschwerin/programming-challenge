package de.exxcellent.challenge.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the Column class representing a column in a table (de/exxcellent/challenge/table/Column.java).
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */

public class TestColumn {

    private Column column;

    @BeforeEach
    public void setUp() {
        List<String> values = new ArrayList<>();
        values.add("Value1");
        values.add("Value2");
        values.add("Value3");
        column = new Column("Test Column", values);
    }

    @Test
    public void testGetValueAtIndex() {
        assertEquals("Value1", column.getValueAtIndex(0));
        assertEquals("Value2", column.getValueAtIndex(1));
        assertEquals("Value3", column.getValueAtIndex(2));
    }

    @Test
    public void testAddValue() {
        column.addValue("Value4");
        assertEquals(4, column.getSize());
        assertEquals("Value4", column.getValueAtIndex(3));
    }

    @Test
    public void testAddValueAtIndex() {
        column.addValueAtIndex("NewValue", 1);
        assertEquals(4, column.getSize());
        assertEquals("NewValue", column.getValueAtIndex(1));
        // Value2 is now at index 2
        assertEquals("Value2", column.getValueAtIndex(2));
    }

    @Test
    public void testRemoveValueAtIndex() {
        column.removeValueAtIndex(1);
        assertEquals(2, column.getSize());
        assertEquals("Value1", column.getValueAtIndex(0));
        // Value3 is now at index 1
        assertEquals("Value3", column.getValueAtIndex(1));
    }

    @Test
    public void testGetSize() {
        assertEquals(3, column.getSize());

        column.setValues(new ArrayList<>());
        assertEquals(0, column.getSize());
    }

    @Test
    public void testRemoveValueAtIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> column.removeValueAtIndex(10));
    }

    @Test
    public void testAddValueAtIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> column.addValueAtIndex("NewValue", 10));
    }

    @Test
    public void testGetValueAtIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> column.getValueAtIndex(10));
    }

    @Test
    public void testRemoveValueAtIndexNegativeIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> column.removeValueAtIndex(-1));
    }

    @Test
    public void testAddValueAtIndexNegativeIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> column.addValueAtIndex("NewValue", -1));
    }

    @Test
    public void testGetValueAtIndexNegativeIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> column.getValueAtIndex(-1));
    }
}
