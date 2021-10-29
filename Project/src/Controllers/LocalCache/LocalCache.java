package Controllers.LocalCache;

import Controllers.DataProcessing.DataFormat;
import Entities.IEntry;
import Entities.Listing.JobListing;
import Entities.User.User;
import UseCase.FileIO.IEntryDeserializer;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.FileIO.MalformedDataException;
import UseCase.IDatabase;
import UseCase.Listing.JobListingDB;
import Framework.FileIO.FileIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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

        for (IEntry entry : listingDB) { // TODO: Get rid of this inline comment below
            if (entry instanceof JobListing) { // insures that the entry is actually a JobListing instead of a different IEntry subclass
                String data = serializer.serialize((HashMap<String, Object>) entry.serialize());
                FileIO.WriteFile(LISTING_SAVE_LOCATION, entry.getSerializedFileName() + ".json", data);
            }
        }
    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingDB with the new
     * one.
     */
    public void loadListingFromUUID(String uuid) {
        String dataString = FileIO.ReadFile(LISTING_SAVE_LOCATION + uuid + ".json");
        try {
            JobListing newJobListing = DataFormat.createListing(dataString);
            listingDB.updateEntry(newJobListing);
        } catch (MalformedDataException e) {
            e.printStackTrace();
        }
    }

    public void loadListingFromUUID(String uuid, IEntryDeserializer deserializer){
        String dataString = FileIO.ReadFile(LISTING_SAVE_LOCATION + uuid + ".json");
        try{
            JobListing newJobListing = DataFormat.createListing(dataString);
            listingDB.updateEntry(newJobListing);
        }
        catch (MalformedDataException e){
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
