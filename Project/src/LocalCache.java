import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalCache {
    public final static HashMap<ListingType, ArrayList<Listing>> listingsMap = new HashMap<>();
    
    //TODO: Complete methods
    /**
     * Calls on FileIO and DataFormat to load listings. Adds the created listing according to listingsMap according
     * to the ListingType Enum (use instanceOf).
     *
     * Load all listings from the folder DemoListings.
     * 
     * For the skeleton program implementation, only CustomListings should exist.
     * 
     * Do not add duplicate listings to listingsMap.
     */
    public static void loadSavedListings(){
        throw new java.lang.UnsupportedOperationException();
    }

    /**
     *
     * Save all listings in localCache,
     *
     * The filename should be the UID of the listing with the extension ".json"
     *
     */
    public static void saveAllListings(){throw new java.lang.UnsupportedOperationException();

    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingsMap with the new
     * one.
     */
    public static void loadListingFromUID(int UID){
        throw new java.lang.UnsupportedOperationException();
    }


}
