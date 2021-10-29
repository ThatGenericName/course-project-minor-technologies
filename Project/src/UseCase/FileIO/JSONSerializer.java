package UseCase.FileIO;

import Entities.IEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JSONSerializer implements IEntrySerializer, IEntryDeserializer{
    @Override
    public String serialize(HashMap<String, Object> serializedHashmap) {


        JSONObject jsonData = new JSONObject(serializedHashmap);

//        jsonData.put("UID", UID);
//        jsonData.put("listingType", listingType);
//        jsonData.put("title", title);
//        jsonData.put("location", location);
//        jsonData.put("pay", pay);
//        jsonData.put("jobType", jobType);
//        jsonData.put("qualifications", qualifications);
//        jsonData.put("requirements", requirements);
//        jsonData.put("applicationReq", applicationRequirements);
//        jsonData.put("description", description);
//        jsonData.put("saved", saved);
//        jsonData.put("listingDate", listingDate);
//        jsonData.put("crossPlatformDuplicates", getCPDIDs());
        return jsonData.toString();
    }

    @Override
    public HashMap<String, Object> Deserialize(String data) {

        try{
            JSONObject jsonData = new JSONObject(data);
            Map<String, Object> deserializeMap = jsonData.toMap();
            return new HashMap<>(deserializeMap);
        }
        catch (JSONException e){
            return null;
        }
    }

}
