package UseCase.Factories.SearchQuery;

import Entities.Entry;
import Entities.SearchQuery.SearchQuery;
import UseCase.FileIO.MalformedDataException;
import UseCase.Factories.ICreateEntry;

import java.util.ArrayList;
import java.util.Map;

public class CreateSearchQuery implements ICreateEntry {

    @Override
    public Entry create(Map<String, Object> entryDataMap) throws MalformedDataException {
        SearchQuery query = new SearchQuery();
        query.deserialize(entryDataMap);
        return query;
    }

    @Override
    public ArrayList<String> verifyMapIntegrity(Map<String, Object> entryDataMap) {
        ArrayList<String> missingKeys = new ArrayList<>();
        for (String key:
                SearchQuery.KEYS) {
            if (!entryDataMap.containsKey(key)){
                missingKeys.add(key);
            }
        }
        return missingKeys;
    }
}
