import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LocalCache {
    public final static HashMap<ListingType, ArrayList<Listing>> listingsMap = new HashMap<>();
    /**
     * Calls on FileIO and DataFormat to load listings. Adds the created listing according to listingsMap according
     * to the ListingType Enum.
     *
     * Load all listings from the folder DemoListings.
     * 
     * For the skeleton program implementation, only CustomListings should exist.
     * 
     * Do not add duplicate listings to listingsMap.
     *
     */
    public static void loadSavedListings(){
        ArrayList<String> fileNames = FileIO.GetFileNamesInDir("\\DemoListings\\", ".json");
        for(String file : fileNames) {
            String jsonDataString = FileIO.ReadFile("\\DemoListings\\" + file);
            try {
                Listing listing = DataFormat.createListing(jsonDataString);
                if(!listingsMap.containsKey(listing.listingType)) {
                    listingsMap.put(listing.listingType, new ArrayList<>());
                }
                if(!listingsMap.get(listing.listingType).contains(listing)) {
                    listingsMap.get(listing.listingType).add(listing);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * Save all listings in LocalCache's listingsMap to relPath,
     *
     * The filename should be the UID of the listing with the extension ".json"
     *
     */
    public static void saveAllListings(){
        Set<ListingType> keys = listingsMap.keySet();
        for(ListingType key : keys){
            for (Listing listing : listingsMap.get(key)){
                String jsonDataString = DataFormat.createJSON(listing);
                FileIO.WriteFile("\\DemoListings\\", listing.getUID() + ".json", jsonDataString);
            }
        }
    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingsMap with the new
     * one.
     *
     */
    public static void loadListingFromUID(int UID) {
        String newJsonDataString = FileIO.ReadFile("\\DemoListings\\" + UID + ".json");
        try {
            Listing newListing = DataFormat.createListing(newJsonDataString);
            for (Iterator<Listing> it = listingsMap.get(newListing.listingType).iterator(); it.hasNext(); ) {
                Listing listing = it.next();
                if (listing.getUID() == UID){
                    listingsMap.get(newListing.listingType).remove(listing);
                    listingsMap.get(newListing.listingType).add(newListing);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a listing with the UID from listingsMap.
     *
     * returns null if there is no listing with the provided UID
     *
     */
    public static Listing getListingFromUID(int UID){
        for (ListingType key :
             listingsMap.keySet()) {
            for (Listing listing:
                 listingsMap.get(key)) {
                if (listing.getUID() == UID){
                    return listing;
                }
            }
        }
        return null;
    }
}
