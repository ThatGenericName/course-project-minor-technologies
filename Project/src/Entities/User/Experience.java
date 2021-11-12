package Entities.User;

import Entities.Entry;
import UseCase.FileIO.MalformedDataException;

import java.util.HashMap;
import java.util.Map;

public class Experience extends Entry {

    public static final String EXPERIENCE_TITLE = "experienceTitle";
    public static final String EXPERIENCE_DESCRPTION = "experienceDescription";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";

    @Override
    public HashMap<String, Object> serialize() {
        return null;
    }

    @Override
    public void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

    }

    @Override
    public String getSerializedFileName() {
        return null;
    }
}
