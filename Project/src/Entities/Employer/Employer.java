package Entities.Employer;

import Entities.IEntry;
import UseCase.FileIO.MalformedDataException;

import java.util.HashMap;
import java.util.Map;

public class Employer implements IEntry {
    @Override
    public HashMap<String, Object> serialize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

    }

    @Override
    public boolean verifyKeyCount(Map<String, Object> entryDataMap) {
        return false;
    }

    @Override
    public String getSerializedFileName() {
        throw new UnsupportedOperationException();
    }
}
