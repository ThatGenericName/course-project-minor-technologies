package UseCase.Listing;

import Entities.Listing.Listing;
import UseCase.IDatabase;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public interface ICreateListing {
    Listing create(JSONObject listingJsonData) throws IOException;

    static @NotNull ArrayList<String> verifyJSONIntegrity(JSONObject listingJsonData) {
        String[] integrity = ("UID listingType title location pay jobType qualifications requirements " +
                "applicationReq description saved listingDate crossPlatformDuplicates").split(" ");
        Set<String> keys = listingJsonData.keySet();

        ArrayList<String> missingKeys = new ArrayList<>();
        for (String key : integrity) {
            if (!keys.contains(key)) {
                missingKeys.add(key);
            }
        }
        return missingKeys;
    }
}
