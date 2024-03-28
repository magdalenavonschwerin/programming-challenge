package de.exxcellent.challenge.handler;

import java.util.List;

/**
 * Interface for a Data handler that can process tabular data and data in String Lists.
 *
 * @author Magdalena von Schwerin
 */
public interface DataHandler {
    String process() throws IllegalStateException;

    int getIndexWithSmallestDistance(List<String> mxtValues, List<String> mntValues);
}
