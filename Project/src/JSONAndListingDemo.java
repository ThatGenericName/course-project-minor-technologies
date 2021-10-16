import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A little demo for writing to and from a json file, and loading it as a Listing Object.
 *
 *
 */
public class JSONAndListingDemo {

    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");

        ListingInOut();

        LocalCache.loadSavedListings();

        Main.user = new User("Test");

        Random rand = new Random();

        int choice = rand.nextInt(LocalCache.listingsMap.get(ListingType.CUSTOM).size());

        Listing watched = LocalCache.listingsMap.get(ListingType.CUSTOM).get(choice);
        Main.user.addListingToWatch(watched);

        System.out.println(watched.getUID());

        BackgroundOperations.startBackgroundLoop();

        int x = 0;

        while(x < 15){
            System.out.print("this is loop {");
            System.out.print(x);
            System.out.println("}");

            HashSet<Integer> UIDS = Main.user.getWatchedListings();

            for (int uid:
                 UIDS) {
                Listing listingRefreshed = LocalCache.getListingFromUID(uid);
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

        String load = "\\ListingInOut\\Load";
        String save = "\\ListingInOut\\Save";
        ArrayList<String> files = FileIO.GetFileNamesInDir(load, ".json");

        for (String file : files) {
            if (!(file.equals("ListingTemplate.json"))){
                try {
                    Listing listing = DataFormat.createListing(FileIO.ReadFile(load + "\\" + file));
                    String listingData = DataFormat.createJSON(listing);
                    String listingUID = Integer.toString(listing.getUID());
                    FileIO.WriteFile(save, listingUID + ".json", listingData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
