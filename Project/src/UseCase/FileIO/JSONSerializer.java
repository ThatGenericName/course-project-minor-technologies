package UseCase.FileIO;

import Entities.IEntry;
import org.json.JSONObject;

import java.util.HashMap;

public class JSONSerializer implements IEntrySerializer{
    @Override
    public String[] serialize(IEntry entry) {

        HashMap<String, Object> serialized = entry.serialize();

        JSONObject jsonData = new JSONObject(serialized);

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
        return new String[]{entry.getSerializedFileName(), jsonData.toString()};
    }
}
