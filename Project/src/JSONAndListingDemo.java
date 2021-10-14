import java.io.IOException;

/**
 * A little demo for writing to and from a json file, and loading it as a Listing Object.
 *
 *
 */
public class JSONAndListingDemo {
    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");


        //Creates a CustomListing through a constructor
        CustomListing demoListing = new CustomListing("DemoTitle", "DemoLocation", 36000, JobType.FULL_TIME,
                "DemoQualifications", "DemoRequirements",
                "DemoApplicationRequirements", "DemoDescription, This could be long?",
                "q.utoronto.ca");

        String demoListingJson = DataFormat.createJSON(demoListing);

        // writes to \DemoListing\DemoListing1.json

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
            Listing demoListing2 = DataFormat.createListing(FileIO.ReadFile("\\DemoListings\\DemoListing2.json"));
            System.out.println(demoListing.equals(demoListing1copy));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
