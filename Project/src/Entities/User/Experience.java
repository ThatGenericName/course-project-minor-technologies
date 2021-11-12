package Entities.User;

import Entities.Entry;
import UseCase.FileIO.MalformedDataException;

import java.util.HashMap;
import java.util.Map;

public class Experience extends Entry {

    public static final String EXPERIENCE_TITLE = "experienceTitle"; // String
    public static final String EXPERIENCE_DESCRPTION = "experienceDescription"; // String
    public static final String START_TIME = "startTime"; // LocalDateTime
    public static final String END_TIME = "endTime"; // LocalDateTime

    @Override
    public synchronized HashMap<String, Object> serialize() {
        return null;
    }

    @Override
    public synchronized void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

    }

    @Override
    public String getSerializedFileName() {
        return null;
    }

    @Override
    public synchronized void updateEntry(Map<String, Object> entryDataMap) {

    }

    @Override
    public synchronized void updateEntry(Entry entry) {

    }
}
