package Controllers.LocalCache;

import Controllers.DataProcessing.DataFormat;
import Entities.IEntry;
import Entities.Listing.JobListing;
import Entities.User.User;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.IDatabase;
import UseCase.Listing.JobListingDB;
import Framework.FileIO.FileIO;

import java.io.File;
import java.io.IOException;

public class LocalCache {


    public final static String LISTING_SAVE_LOCATION = "DemoListings" + File.separator;
    private IDatabase listingDB;
    private User currentActiveUser;

    public LocalCache(){
        loadSavedListings();
        createUser();
    }

    public IDatabase getListingDB() {
        return listingDB;
    }

    public User getCurrentActiveUser() {
        return currentActiveUser;
    }

    public void setCurrentActiveUser(User currentActiveUser) {
        this.currentActiveUser = currentActiveUser;
    }

    public void createUser(){

    }

    /**
     * Calls on FileIO and DataFormat to load listings. Adds the created listing according
     * to listingDB according to the Entities.Listings.ListingType Enum.
     *
     * Load all listings from the folder DemoListings.
     * 
     * For the skeleton program implementation, only CustomListings should exist.
     * 
     * Do not add duplicate listings to listingDB.
     *
     */

    public void loadSavedListings(){
        listingDB = new JobListingDB(DataFormat.loadListingsFromFileDirectory(LISTING_SAVE_LOCATION));
    }

    /**
     *
     * Save all listings in Controllers.LocalCache.LocalCache's listingDB to relPath,
     *
     * The filename should be the UID of the listing with the extension ".json"
     *
     */
    @Deprecated
    public void saveAllListingsOld(){

        for (IEntry entry : listingDB) {
            if (entry instanceof JobListing) {
                JobListing jobListing = (JobListing) entry;
                String jsonDataString = DataFormat.createJSON(jobListing);
                FileIO.WriteFile(LISTING_SAVE_LOCATION, jobListing.getUUID() + ".json", jsonDataString);
            }
        }
    }

    public void saveAllListings(){

        IEntrySerializer serializer = new JSONSerializer();

        saveAllListings(serializer);
    }

    public void saveAllListings(IEntrySerializer serializer){

        for (IEntry entry : listingDB) {
            if (entry instanceof JobListing) {
                String[] data = serializer.serialize(entry);
                FileIO.WriteFile(LISTING_SAVE_LOCATION, data[0] + ".json", data[1]);
            }
        }
    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingDB with the new
     * one.
     */
    public void loadListingFromUID(String uuid) {
        String newJsonDataString = FileIO.ReadFile(LISTING_SAVE_LOCATION + uuid + ".json");
        try {
            JobListing newJobListing = DataFormat.createListing(newJsonDataString);
            listingDB.updateEntry(newJobListing);
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
    public JobListing getListingFromUUID(String uuid){
        for (Object entry:
             listingDB) {
            if (entry instanceof JobListing){
                JobListing jobListing = (JobListing) entry;
                if (jobListing.getUUID() == uuid){
                    return jobListing;
                }
            }
        }
        return null;
    }
}
