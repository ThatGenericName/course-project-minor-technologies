package UseCase;

import Entities.IEntry;
import Entities.User.User;
import UseCase.FileIO.MalformedDataException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface ICreateEntry {

    IEntry create(Map<String, Object> UserDataMap) throws IOException, MalformedDataException;

    ArrayList<String> verifyMapIntegrity(Map<String, Object> userDataMap);

    static String missingKeyInfo(ArrayList<String> keys, String type){
        StringBuilder msg = new StringBuilder("JSON data for {" + type + "} listing missing keys:");
        for (String key :
                keys) {
            msg.append(" {").append(key).append("},");
        }

        return msg.toString();
    }
}
