import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A little demo for writing to and from a json file, and loading it as a Listing Object.
 *
 *
 */
public class JSONAndListingDemo {

    public static CustomListing demo1 = null;

    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");

        ListingInOut();

        Main.user = new User("Test");

        //Creates a CustomListing through a constructor
        CustomListing demoListing = new CustomListing("DemoTitle", "DemoLocation", 36000, JobType.FULL_TIME,
                "DemoQualifications", "DemoRequirements",
                "DemoApplicationRequirements", "DemoDescription, This could be long?",
                "q.utoronto.ca");

        String demoListingJson = DataFormat.createJSON(demoListing);

        // writes to \DemoListing\DemoListing1.json
        Listing demoListing2 = null;

        if (FileIO.WriteFile("\\DemoListings", "\\DemoListing1.json", demoListingJson)){
            System.out.println("File Written!");
        }
        else{
            System.out.println("File Not Written!");
        }

        // the below could also be an implementation of how LocalCache reads and writes files
        try {
            //Set a breakpoint here, see if the 2 objects really have the same data.
            Listing demoListing1copy = DataFormat.createListing(FileIO.ReadFile("\\DemoListings\\DemoListing1.json"));

            //heres a completely new listing, read from DemoListing2.json
            demoListing2 = DataFormat.createListing(FileIO.ReadFile("\\DemoListings\\DemoListing2.json"));
            System.out.println(demoListing.equals(demoListing1copy));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.user.addListingToWatch(demoListing2);

        BackgroundOperations.startBackgroundLoop();

        int x = 0;

        while(x < 15){
            System.out.print("this is loop {");
            System.out.print(x);
            System.out.println("}");
            if (!(demo1 == null)){
                System.out.println(demo1.getDescription());
            }
            else{
                System.out.println("null");
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
     * reads all files (thats not named "ListingTemplate.json") from \ListingInOut\Load and
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
