package UseCase.Listing;

import Entities.Listing.CustomListing;
import Entities.Listing.Listing;
import UseCase.IDatabase;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class CreateCustomListing implements ICreateListing {

    public CreateCustomListing(){
    }
    @Override
    public Listing create(JSONObject listingJsonData) throws IOException {
        ArrayList<String> missingKeys = verifyJSONIntegrity(listingJsonData);
        if (missingKeys.size() == 0){
            return new CustomListing(listingJsonData);
        }
        else{
            StringBuilder exceptionMessage = new StringBuilder("JSONData missing keys:");
            for (String key:
                 missingKeys) {
                exceptionMessage.append(" {").append(key).append("}");
            }
            throw new IOException(exceptionMessage.toString());
        }
    }

    public static @NotNull ArrayList<String> verifyJSONIntegrity(JSONObject listingJsonData){
        ArrayList<String> missingKeys = ICreateListing.verifyJSONIntegrity(listingJsonData);
        Set<String> keys = listingJsonData.keySet();

        if (!keys.contains("origin")){
            missingKeys.add("origin");
        }
        return missingKeys;
    }
}
