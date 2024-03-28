package de.exxcellent.challenge.reader;

import java.util.List;

/**
 * Interface for a generic reader that takes a file path and reads the file into a String Array.
 *
 * @author Magdalena von Schwerin <magdalena.vonschwerin@gmail.com><
 */
public interface FileReader {
    List<String> readFile(String filePath) throws IllegalArgumentException;
}
