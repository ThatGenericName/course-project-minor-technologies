package com.minortechnologies.workr.UseCase.Factories.SearchQuery;

import com.minortechnologies.workr.Entities.Entry;
import com.minortechnologies.workr.Entities.SearchQuery.SearchQuery;
import com.minortechnologies.workr.UseCase.FileIO.MalformedDataException;
import com.minortechnologies.workr.UseCase.Factories.ICreateEntry;

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
