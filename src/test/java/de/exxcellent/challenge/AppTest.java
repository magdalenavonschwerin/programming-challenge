package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class for the main App.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com>
 */

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture the output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testMainWeatherArgument() {
        App.main("--weather", "weather.csv");
        String output = outputStream.toString().trim();
        assertEquals("Day with smallest temperature spread : 14", output);
    }

    @Test
    public void testMainFootballArgument() {
        App.main("--football", "football.csv");
        String output = outputStream.toString().trim();
        assertEquals("Team with smallest goal spread : Aston_Villa", output);
    }

    /**
     * Test for the exit code triggered by
     *  too many arguments
     *  too few arguments
     *  arguments == null
     *  invalid arguments (challenge or csv)
     */
    @Test
    public void testMainInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> App.main("--weather", "csv_file.csv", "note"));
        assertThrows(IllegalArgumentException.class, () -> App.main("--football"));
        assertThrows(NullPointerException.class, () -> App.main("--weather", null));

        assertThrows(IllegalArgumentException.class, () -> App.main("--basketball", "basketball.csv"));

        assertThrows(IllegalArgumentException.class, () -> App.main("--weather", "invalid.csv"));
        assertThrows(IllegalArgumentException.class, () -> App.main("--weather", "invalid.txt"));
        assertThrows(IllegalArgumentException.class, () -> App.main("--football", "weather"));
        assertThrows(IllegalArgumentException.class, () -> App.main("--weather", "  "));
    }

}

