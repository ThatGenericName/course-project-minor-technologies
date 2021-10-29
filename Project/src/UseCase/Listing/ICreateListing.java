package UseCase.Listing;

import Entities.Listing.JobListing;
import Entities.Listing.ListingType;
import UseCase.FileIO.MalformedDataException;
import UseCase.ICreateEntry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Usecase Factory Interface for creating a specific listing type.
 *
 * subclasses of this must implement verifyJsonIntegrity and also call this interface's
 * implementation of verifyJSONIntegrity as it checks for keys required by all listing types.
 *
 *
 */
public interface ICreateListing extends ICreateEntry {
    JobListing create(Map<String, Object> listingDataMap) throws MalformedDataException;

    /**
     * Factory method to create a listing.
     *
     * @param listingMapData - a Map containing the listing data
     * @return A listing created based on the listingMapData
     */
    static JobListing createListing(Map<String, Object> listingMapData) throws MalformedDataException {
        ArrayList<String> missingKeys = verifyMapIntegrityStatic(listingMapData);
        if (missingKeys.size() == 0){
            ListingType type = ListingType.valueOf((String) listingMapData.get("listingType"));
            switch(type) {
                case CUSTOM:
                    return new CreateCustomListing().create(listingMapData);
                case LINKED_IN:
                case INDEED:
                    throw new java.lang.UnsupportedOperationException();
                default:
                    throw new MalformedDataException();
            }
        }

        throw new MalformedDataException(missingKeyInfo(missingKeys, "UNCATEGORIZED"));
    }

    /**
     * Verifies Data integrity by checking that all required keys are present.
     *
     * @param listingDataMap the map containing the Data for the Listing
     * @return An Arraylist containing any keys missing from the Map. This list is empty if there are no missing keys.
     */
    static @NotNull ArrayList<String> verifyMapIntegrityStatic(Map<String, Object> listingDataMap) {
        String[] integrity = ("UUID listingType title location pay jobType qualifications requirements " +
                "applicationReq description saved listingDate crossPlatformDuplicates").split(" ");
        Set<String> keys = listingDataMap.keySet();

        ArrayList<String> missingKeys = new ArrayList<>();
        for (String key : integrity) {
            if (!keys.contains(key)) {
                missingKeys.add(key);
            }
        }
        return missingKeys;
    }

    static String missingKeyInfo(ArrayList<String> keys, String type){
        StringBuilder msg = new StringBuilder("JSON data for {" + type + "} listing missing keys:");
        for (String key :
                keys) {
            msg.append(" {").append(key).append("},");
        }

        return msg.toString();
    }
}
