import java.io.IOException;

/**
 * A little demo for writing to and from a json file, and loading it as a Listing Object.
 *
 *
 */
public class JSONandListingDemo {
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

        try {
            //Set a breakpoint here, see if the 2 objects really have the same data.
            Listing demoListing2 = DataFormat.createListing(FileIO.ReadFile("\\DemoListings\\DemoListing1.json"));

            System.out.println(demoListing.equals(demoListing2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
