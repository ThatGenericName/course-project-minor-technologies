package UseCase.Factories.UserFactory;

import Entities.Entry;
import Entities.User.Experience;
import UseCase.Factories.ICreateEntry;
import UseCase.FileIO.MalformedDataException;

import java.io.IOException;
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
