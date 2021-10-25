package Controllers.DataProcessing;

import Entities.Listing.JobListing;
import Framework.FileIO.FileIO;
import UseCase.Listing.ICreateListing;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class DataFormat {

    /**
     * Creates a Listing from the jsonDataString
     * @param jsonDataString - String representing the listing data in JSON format
     * @return Listing - The Listing created form the JSONData.
     * @throws IOException
     */
    public static JobListing createListing(String jsonDataString) throws IOException {
        try{
            JSONObject jsonData = new JSONObject(jsonDataString);

            return ICreateListing.createListing(jsonData);
        }
        catch (JSONException e){
            throw new IOException(e);
        }
    }

    /**
     * Creates a string in JSON format that represents listing
     *
     * @param jobListing the listing to create a JSON Formatted string for
     * @return a string in JSON format representing the data of the listing
     */
    @Deprecated
    public static String createJSON(JobListing jobListing){
        return jobListing.serialize().toString();
    }


    public static ArrayList<JobListing> loadListingsFromFileDirectory(String relPath){

        ArrayList<String> fileNames = FileIO.GetFileNamesInDir(relPath, ".json");
        ArrayList<JobListing> jobListings = new ArrayList<>();

        for(String file : fileNames) {
            String datatString = FileIO.ReadFile(relPath + file);
            try {
                JobListing jobListing = createListing(datatString);
                jobListings.add(jobListing);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jobListings;
    }
}
