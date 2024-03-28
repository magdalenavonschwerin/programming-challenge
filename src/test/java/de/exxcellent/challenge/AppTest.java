package de.exxcellent.challenge;

import de.exxcellent.challenge.handler.WeatherHandler;
import de.exxcellent.challenge.reader.Reader;
import de.exxcellent.challenge.table.Table;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture the output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testMainWeatherArgument() {
        App.main("--weather", "weather.csv");
        String output = outputStream.toString().trim();
        assertEquals("Day with smallest temperature spread : 14", output);
    }

    /**
     * Test for the exit code triggered by
     * invalid arguments (challenge or csv)
     * too many arguments
     * too few arguments
     * arguments == null
     */
    @Test
    public void testMainInvalidArgument() {
        App.main("--invalid", "invalid.csv");

        ProcessBuilder processBuilder = new ProcessBuilder("java", "App", "--weather", "weather.csv");
        int exitCode = -1;
        try {
            Process process = processBuilder.start();
            exitCode = process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(1, exitCode);
    }


    /*
      TODO test like above
     */
    @Test
    public void testMainWithInvalidCsvFile() {
        App.main("--weather", null);
        App.main("--weather", "invalid.csv");
        App.main("--weather", "invalid.txt");
        App.main("--weather", "weather");
        App.main("--weather", "  ");
    }



}

