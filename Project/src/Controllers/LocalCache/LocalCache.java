package Controllers.LocalCache;

import Controllers.DataFormat;
import Entities.User.User;
import Entities.Listing.Listing;
import Entities.Listing.ListingType;
import UseCase.Listing.ListingDB;
import Framework.FileIO.FileIO;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LocalCache {


    public final static String LISTING_SAVE_LOCATION = "DemoListings" + File.separator;
    public static ListingDB listingDB;
    private static User currentActiveUser;

    public static void initiateLocalCache(){
        loadSavedListings();
        createUser();
    }

    public static User getCurrentActiveUser() {
        return currentActiveUser;
    }

    public static void setCurrentActiveUser(User currentActiveUser) {
        LocalCache.currentActiveUser = currentActiveUser;
    }

    /**
     * Calls on Framework.FileIO.FileIO and Controllers.DataFormat to load listings. Adds the created listing according
     * to listingDB according to the Entities.Listings.ListingType Enum.
     *
     * Load all listings from the folder DemoListings.
     * 
     * For the skeleton program implementation, only CustomListings should exist.
     * 
     * Do not add duplicate listings to listingDB.
     *
     */
    public static void createUser(){

    }

    public static void loadSavedListings(){
        listingDB = new ListingDB(DataFormat.loadListingsFromFileDirectory(LISTING_SAVE_LOCATION));
    }

    @Deprecated
    public static void loadSavedListingsDep(){
        ArrayList<String> fileNames = FileIO.GetFileNamesInDir(LISTING_SAVE_LOCATION, ".json");
        for(String file : fileNames) {
            String jsonDataString = FileIO.ReadFile(LISTING_SAVE_LOCATION + file);
            try {
                Listing listing = DataFormat.createListing(jsonDataString);
                if(!listingDB.containsKey(listing.getListingType())) {
                    listingDB.put(listing.getListingType(), new ArrayList<>());
                }
                if(!listingDB.get(listing.getListingType()).contains(listing)) {
                    listingDB.get(listing.getListingType()).add(listing);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * Save all listings in Controllers.LocalCache.LocalCache's listingDB to relPath,
     *
     * The filename should be the UID of the listing with the extension ".json"
     *
     */
    public static void saveAllListings(){
        Set<ListingType> keys = listingDB.keySet();
        for(ListingType key : keys){
            for (Listing listing : listingDB.get(key)){
                String jsonDataString = DataFormat.createJSON(listing);
                FileIO.WriteFile( LISTING_SAVE_LOCATION, listing.getUID() + ".json", jsonDataString);
            }
        }
    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingDB with the new
     * one.
     *
     */
    public static void loadListingFromUID(int UID) {
        String newJsonDataString = FileIO.ReadFile(LISTING_SAVE_LOCATION + UID + ".json");
        try {
            Listing newListing = DataFormat.createListing(newJsonDataString);
            int dupeInd = containsUID(newListing.getListingType(), UID);
            if (dupeInd != -1){
                listingDB.get(newListing.getListingType()).remove(dupeInd);
            }
            listingDB.get(newListing.getListingType()).add(newListing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the Index of a listing with the given UID and the listingType.
     *
     * returns -1 if no listings have that type.
     */
    private static int containsUID(ListingType listingType, int UID){
        for (int i = 0; i < listingDB.get(listingType).size(); i++) {
            if (listingDB.get(listingType).get(i).getUID() == UID){
                return i;
            }
        }
        return -1;
    }

    /**
     * returns a listing with the UID from listingDB.
     *
     * returns null if there is no listing with the provided UID
     *
     */
    public static Listing getListingFromUID(int UID){
        for (ListingType key :
                listingDB.keySet()) {
            for (Listing listing:
                    listingDB.get(key)) {
                if (listing.getUID() == UID){
                    return listing;
                }
            }
        }
        return null;
    }
}
