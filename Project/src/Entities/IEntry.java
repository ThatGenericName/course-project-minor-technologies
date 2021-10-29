package Entities;

import UseCase.FileIO.MalformedDataException;

import java.util.HashMap;
import java.util.Map;

public interface IEntry {

    public static final String MALFORMED_EXCEPTION_MSG = "Malformed data detected during construction, perhaps Data Map integrity was not checked before construction?";

    /**
     * Prepares a Map representing the Data for serialization.
     *
     * @return A Map representing the data for this Entry.
     */
    Map<String, Object> serialize();

    // TODO: set a better name as this might not always be called for serialization purposes.

    /**
     * loads instance variables for the Entry object from deserialized data (in a form of a map).
     *
     * Precondition: Deserialized Data is not malformed (missing keys, etc.).
     * A simple key-count check is performed (through verifyKeySize). If the check fails, throws
     * a MalformedDataException.
     *
     * @param entryDataMap - the deserialized data stored in a Map data type
     */
    void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException;

    /**
     * Verifies that the Data Map for the entry contains the correct number of keys.
     *
     * @param entryDataMap The entryDataMap to verify key count
     * @return boolean whether they contained the correct number of keys.
     */
    boolean verifyKeyCount(Map<String, Object> entryDataMap);

    /**
     * Generates the filename for serialization purposes.
     *
     * @return String the filename (without any file extensions).
     */
    String getSerializedFileName();
}
