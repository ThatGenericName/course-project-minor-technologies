package Entities.Employer;

import Entities.Entry;
import UseCase.FileIO.MalformedDataException;
import org.apache.commons.lang3.SerializationUtils;

import java.util.HashMap;
import java.util.Map;

public class Employer extends Entry {

    public final String EMPLOYER_NAME = "employerName";
    public final String EMPLOYER_LISTINGS = "employerListings";



    @Override
    public synchronized HashMap<String, Object> serialize() {

        return SerializationUtils.clone(getEntryData());
    }

    @Override
    public synchronized void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

    }

    @Override
    public synchronized String getSerializedFileName() {
        return null;
    }
}
