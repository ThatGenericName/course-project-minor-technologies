package UseCase.Listing;

import Entities.Listing.JobListing;
import Entities.Listing.ListingType;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Usecase Factory Interface for creating a specific listing type.
 *
 * subclasses of this must implement verifyJsonIntegrity and also call this interface's
 * implementation of verifyJSONIntegrity as it checks for keys required by all listing types.
 *
 *
 */
public interface ICreateListing {
    JobListing create(JSONObject listingJsonData) throws IOException;

    /**
     * Factory method to create a listing.
     *
     * @param listingJsonData JSONObject containing the listing data
     * @return A listing created based on the listingJsonData
     */
    static JobListing createListing(JSONObject listingJsonData)throws IOException{
        ArrayList<String> missingKeys = verifyJSONIntegrity(listingJsonData);
        if (missingKeys.size() == 0){
            ListingType type = ListingType.valueOf((String) listingJsonData.get("listingType"));
            switch(type) {
                case CUSTOM:
                    return new CreateCustomListing().create(listingJsonData);
                case LINKED_IN:
                case INDEED:
                    throw new java.lang.UnsupportedOperationException();
                default:
                    throw new IOException();
            }
        }

        throw new IOException(missingKeyInfo(missingKeys, "UNCATEGORIZED"));
    }

    /**
     * Verifies JSON Integrity by checking that all keys that are required are present.
     *
     * @param listingJsonData
     * @return
     */
    static @NotNull ArrayList<String> verifyJSONIntegrity(JSONObject listingJsonData) {
        String[] integrity = ("UUID listingType title location pay jobType qualifications requirements " +
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

    static String missingKeyInfo(ArrayList<String> keys, String type){
        String msg = "JSON data for {" + type + "} listing missing keys:";
        for (String key :
                keys) {
            msg += " {" + key + "},";
        }

        return msg;
    }
}
