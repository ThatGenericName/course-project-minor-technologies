package Entities.User;

import Entities.Entry;
import UseCase.Factories.ICreateEntry;
import UseCase.FileIO.MalformedDataException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Experience extends Entry {

    public static final String EXPERIENCE_TITLE = "experienceTitle"; // String
    public static final String EXPERIENCE_DESCRPTION = "experienceDescription"; // String
    public static final String START_TIME = "startTime"; // LocalDateTime
    public static final String END_TIME = "endTime"; // LocalDateTime

    public static final String[] KEYS = new String[]{EXPERIENCE_TITLE, EXPERIENCE_DESCRPTION, START_TIME, END_TIME};

    @Override
    public synchronized HashMap<String, Object> serialize() {
        HashMap<String, Object> dataMap = new HashMap<>();
        for (String key:
             KEYS) {
            Object data = getData(key);
            dataMap.put(key, data);
        }
        return dataMap;
    }

    @Override
    public synchronized void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {
        for (String key:
             KEYS) {
            Object data = entryDataMap.get(key);
            addData(key, data);
        }
    }

    /**
     * This entry should never be serialized on its own, It should always be serialized as part of a user entry,
     * therefore it returns null.
     *
     * @return
     */
    @Override
    public String getSerializedFileName() {
        return null;
    }

    @Override
    public synchronized void updateEntry(Map<String, Object> entryDataMap) {
        for (String key:
                KEYS) {
            Object data = entryDataMap.get(key);
            updateData(key, data);
        }
    }

    @Override
    public synchronized void updateEntry(Entry entry) {
        Map<String, Object> entryDataMap = entry.serialize();

        updateEntry(entryDataMap);
    }
}
