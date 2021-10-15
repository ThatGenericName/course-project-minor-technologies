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
     * @param relDir the directory relative to the root directory.
     * @param extension the extension to filter by, ie ".json"
     */
    public static void loadSavedListings(String relDir, String extension) throws IOException {
        ArrayList<String> fileNames = FileIO.GetFileNamesInDir(relDir, extension);
        for(String file : fileNames) {
            String jsonDataString = FileIO.ReadFile(file);
            Listing listing = DataFormat.createListing(jsonDataString);
            if(!listingsMap.containsKey(listing.listingType)) {
                listingsMap.put(listing.listingType, new ArrayList<>());
            }
            if(!listingsMap.get(listing.listingType).contains(listing)) {
                listingsMap.get(listing.listingType).add(listing);
            }
        }
    }

    /**
     *
     * Save all listings in LocalCache's listingsMap to relPath,
     *
     * The filename should be the UID of the listing with the extension ".json"
     *
     * @param relPath - The path relative to the root directory of the project to write the file to.
     */
    public static void saveAllListings(String relPath){
        Set<ListingType> keys = listingsMap.keySet();
        for(ListingType key : keys){
            for (Listing listing : listingsMap.get(key)){
                String jsonDataString = DataFormat.createJSON(listing);
                FileIO.WriteFile(relPath, listing.getUID() + ".json", jsonDataString);
            }
        }
    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingsMap with the new
     * one.
     */
    public static void loadListingFromUID(int UID){
        throw new java.lang.UnsupportedOperationException();
    }


}
