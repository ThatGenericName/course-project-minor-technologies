package com.minortechnologies.workr.UseCase.Factories.UserFactory;

import com.minortechnologies.workr.Entities.Entry;
import com.minortechnologies.workr.Entities.User.Experience;
import com.minortechnologies.workr.UseCase.Factories.ICreateEntry;
import com.minortechnologies.workr.UseCase.FileIO.MalformedDataException;

import java.util.ArrayList;
import java.util.Map;

public class CreateExperience implements ICreateEntry {
    @Override
    public Entry create(Map<String, Object> entryDataMap) throws MalformedDataException {

        Experience experience = new Experience();
        experience.deserialize(entryDataMap);

        return experience;
    }

    @Override
    public ArrayList<String> verifyMapIntegrity(Map<String, Object> entryDataMap) {
        ArrayList<String> missingKeys = new ArrayList<>();
        for (String key:
                Experience.KEYS) {
            if (!entryDataMap.containsKey(key)){
                missingKeys.add(key);
            }
        }
        return missingKeys;
    }
}
