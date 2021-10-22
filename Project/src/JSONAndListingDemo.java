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
import Entities.SearchQuery.SearchQuery;
import Entities.Listing.JobType;
import Entities.Listing.Listing;
import Entities.User.User;
import Framework.FileIO.FileIO;
import Main.Main;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;

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
public class JSONAndListingDemo {

    private static LocalCache localCache;

    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");

        ListingInOut();

        localCache = new LocalCache();

        Main.setLocalCache(localCache);

        localCache.loadSavedListings();

        Period period = Period.ofDays(1); // Period.ofMonths(int) for months, Period.ofYears(int) for years
        LocalDateTime time = (LocalDateTime) period.subtractFrom(LocalDateTime.now());

        SearchQuery q = new SearchQuery("Vorp", "Toronto", time , JobType.FULL_TIME);

        HashMap<String, ArrayList<Listing>> sample = Search.searchLocalCache(q);
        System.out.println(sample);

        Main.user = new User("Test");

        Random rand = new Random();

        ArrayList<Listing> allListings = new ArrayList<>();

        for (IEntry entry:
                localCache.getListingDB()) {
            allListings.add((Listing) entry);
        }

        int choice = rand.nextInt(allListings.size());

        Listing watched = allListings.get(choice);
        Main.user.addListingToWatch(watched);

        System.out.println(watched.getUID());

        BackgroundOperations.startBackgroundLoop();

        int x = 0;

        while(x < 15){
            System.out.print("this is loop {");
            System.out.print(x);
            System.out.println("}");

            HashSet<Listing> listings = Main.user.getWatchedListings();

            for (Listing listing:
                 listings) {
                Listing listingRefreshed = localCache.getListingFromUID(listing.getUID());
                if (!(listingRefreshed == null)){
                    System.out.println(listingRefreshed.getDescription());
                }
                else{
                    System.out.println("listing not found");
                }
            }
            x++;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BackgroundOperations.endBackgroundThreads();

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
                    Listing listing = DataFormat.createListing(FileIO.ReadFile(load + File.separator + file));

                    IEntrySerializer serializer = new JSONSerializer();

                    //String listingData = DataFormat.createJSON(listing);

                    String[] listingData = serializer.serialize(listing);

                    FileIO.WriteFile(save, listingData[0] + ".json", listingData[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
