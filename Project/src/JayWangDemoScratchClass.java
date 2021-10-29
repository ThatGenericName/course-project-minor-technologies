/**
 * This is a scratch file that exists purely for debugging and internal demos.
 *
 * The contents of this file does not affect the rest of the program in any way.
 *
 */

import Controllers.BackgroundOperations.BackgroundOperations;
import Controllers.DataProcessing.DataFormat;
import Controllers.LocalCache.LocalCache;
import Controllers.Search.Search;
import Entities.IEntry;
import Entities.Listing.JobListing;
import Entities.SearchQuery.SearchQuery;
import Entities.Listing.JobType;
import Entities.User.User;
import Framework.FileIO.FileIO;
import Main.Main;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.FileIO.MalformedDataException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

/**
 * A little demo for writing to and from a json file, and loading it as a Entities.Listings.Listing Object.
 *
 *
 *
 */
public class JayWangDemoScratchClass {

    private static LocalCache localCache;

    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");

        ListingInOut();

        localCache = new LocalCache();

        Main.setLocalCache(localCache);

        localCache.loadSavedListings();


        System.out.println("ended");
    }

    /**
     * reads all files (that's not named "ListingTemplate.json") from \ListingInOut\Load and
     * saves it in \ListingInOut\Save for UID, Hashing, and Filename generation
     *
     */
    public static void ListingInOut(){

        String base = File.separator + "ListingInOut" + File.separator;

        String load = base + "Load";
        String save = base + "Save";
        ArrayList<String> files = FileIO.GetFileNamesInDir(load, ".json");

        for (String file : files) {
            if (!(file.equals("ListingTemplate.json"))){
                try {
                    JobListing jobListing = DataFormat.createListing(FileIO.ReadFile(load + File.separator + file));

                    IEntrySerializer serializer = new JSONSerializer();

                    //String listingData = DataFormat.createJSON(listing);

                    String listingData = serializer.serialize(jobListing.serialize());

                    FileIO.WriteFile(save, jobListing.getSerializedFileName() + ".json", listingData);
                } catch (MalformedDataException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
