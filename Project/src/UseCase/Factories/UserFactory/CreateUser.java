package UseCase.Factories.UserFactory;

import Entities.Entry;
import Entities.User.User;
import UseCase.Factories.ICreateEntry;
import UseCase.FileIO.MalformedDataException;
import UseCase.Security.Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CreateUser implements ICreateUser{

    @Override
    public User create(Map<String, Object> userDataMap) throws MalformedDataException {
        ArrayList<String> missingKeys = verifyMapIntegrity(userDataMap);
        if (missingKeys.size() != 0){
            String message = ICreateEntry.missingKeyInfo(missingKeys, "User");
            throw new MalformedDataException(message);
        }
        ArrayList<HashMap<String, Object>> queriesDataMap =
                (ArrayList<HashMap<String, Object>>) userDataMap.get(User.WATCHED_SEARCH_QUERIES);
        HashSet<Entry> queries = getDeserializedQueries(queriesDataMap);
        userDataMap.replace(User.WATCHED_SEARCH_QUERIES, queries);
        User user = new User();
        user.deserialize(userDataMap);
        return user;
    }

    private HashSet<Entry> getDeserializedQueries(ArrayList<HashMap<String, Object>> queryData){
        HashSet<Entry> queries = new HashSet<>();
        for (HashMap<String, Object> query:
             queryData) {
            try {
                Entry entry = ICreateEntry.createEntry(query);
                queries.add(entry);
            } catch (MalformedDataException e) {
                e.printStackTrace();
            }
        }
        return queries;
    }

    @Override
    public User create(String username, String login, String password) {
        byte[] saltArr = Security.generateSalt();
        String salt = Security.toHex(saltArr);
        String hashedPassword = Security.toHex(Security.generateHash(password, saltArr));

        return new User(username, login, hashedPassword, salt);
    }

    @Override
    public ArrayList<String> verifyMapIntegrity(Map<String, Object> userDataMap) {
        ArrayList<String> missingKeys = new ArrayList<>();
        for (String key:
             User.KEYS) {
            if (!userDataMap.containsKey(key)){
                missingKeys.add(key);
            }
        }
        return missingKeys;
    }
}
