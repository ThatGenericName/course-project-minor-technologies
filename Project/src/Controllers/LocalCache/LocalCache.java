package Controllers.LocalCache;

import Controllers.DataFormat;
import Entities.IEntry;
import Entities.User.User;
import Entities.Listing.Listing;
import Entities.Listing.ListingType;
import UseCase.IDatabase;
import UseCase.Listing.ListingDB;
import Framework.FileIO.FileIO;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LocalCache {


    public final static String LISTING_SAVE_LOCATION = "DemoListings" + File.separator;
    private static IDatabase listingDB;
    private static User currentActiveUser;

    public static IDatabase getListingDB() {
        return listingDB;
    }

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

    public static void createUser(){

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

    public static void loadSavedListings(){
        listingDB = new ListingDB(DataFormat.loadListingsFromFileDirectory(LISTING_SAVE_LOCATION));
    }

    /**
     *
     * Save all listings in Controllers.LocalCache.LocalCache's listingDB to relPath,
     *
     * The filename should be the UID of the listing with the extension ".json"
     *
     */
    public static void saveAllListings(){

        for (Object entry : listingDB) { //TODO: Find out why it expects this to give an "object" as opposed to IEntry
            if (entry instanceof Listing) {
                Listing listing = (Listing) entry;
                String jsonDataString = DataFormat.createJSON(listing);
                FileIO.WriteFile(LISTING_SAVE_LOCATION, listing.getUID() + ".json", jsonDataString);
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
            listingDB.updateEntry(newListing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a listing with the UID from listingDB.
     *
     * returns null if there is no listing with the provided UID
     *
     */
    public static Listing getListingFromUID(int UID){
        for (Object entry:
             listingDB) {
            if (entry instanceof Listing){
                Listing listing = (Listing) entry;
                if (listing.getUID() == UID){
                    return listing;
                }
            }
        }
        return null;
    }
}
