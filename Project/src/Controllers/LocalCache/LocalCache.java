package Controllers.LocalCache;

import Controllers.DataProcessing.DataFormat;
import Entities.Entry;
import Entities.Listing.JobListing;
import UseCase.FileIO.IEntryDeserializer;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.FileIO.MalformedDataException;
import UseCase.IDatabase;
import UseCase.Listing.JobListingDB;
import Framework.FileIO.FileIO;

import java.io.File;
import java.util.Objects;

public class LocalCache {


    public final static String LISTING_SAVE_LOCATION = "DemoListings" + File.separator;
    private IDatabase listingDB;

    public LocalCache(){
        loadSavedListings();
    }

    public IDatabase getListingDB() {
        return listingDB;
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
        listingDB = new JobListingDB(DataFormat.loadEntriesFromDirectory(FileIO.SAVED_LISTINGS));
    }

    public void saveAllListings(){

        IEntrySerializer serializer = new JSONSerializer();

        saveAllListings(serializer);
    }

    public void saveAllListings(IEntrySerializer serializer){

        for (Entry entry : listingDB) { // TODO: Get rid of this inline comment below
            if (entry instanceof JobListing) { // insures that the entry is actually a JobListing instead of a different IEntry subclass
                String data = serializer.serialize(entry.serialize());
                String saveName = "entry_" + entry.getSerializedFileName() + ".json";
                FileIO.WriteFile(LISTING_SAVE_LOCATION, saveName, data);
            }
        }
    }

    /**
     * Loads a listing's JSON data and updates the listing, replacing the original instance in listingDB with the new
     * one.
     */
    public void updateEntryByUUID(String uuid) {
        String dataString = FileIO.readFile(LISTING_SAVE_LOCATION + uuid + ".json");
        try {
            Entry newJobListing = DataFormat.createEntry(dataString);
            assert newJobListing instanceof JobListing; // TODO: Make this a proper if/else test
            listingDB.updateEntry(newJobListing);
        } catch (MalformedDataException e) {
            e.printStackTrace();
        }
    }

    public void updateEntryByUUID(String uuid, IEntryDeserializer deserializer){
        String dataString = FileIO.readFile(LISTING_SAVE_LOCATION + uuid + ".json");
        try{
            Entry newJobListing = DataFormat.createEntry(dataString);
            assert newJobListing instanceof JobListing; // TODO: Make this a proper if/else test
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
                if (Objects.equals(jobListing.getUUID(), uuid)){
                    return jobListing;
                }
            }
        }
        return null;
    }

    public void addJobListing(Entry jobListing){
        Entry equiv = listingDB.getEquivalent(jobListing);
        if (equiv != null){
            equiv.updateEntry(jobListing);
        }
        listingDB.addEntry(jobListing);
    }
}
