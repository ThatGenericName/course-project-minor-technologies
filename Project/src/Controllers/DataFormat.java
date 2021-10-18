package Controllers;

import Entities.Listing.CustomListingBuilder;
import Entities.Listing.Listing;
import Entities.Listing.ListingType;
import Framework.FileIO.FileIO;
import UseCase.Listing.CreateCustomListing;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class DataFormat {

    /**
     * Creates a Entities.Listings.Listing from the jsonDataString
     * @param jsonDataString - String representing the listing data in JSON format
     * @return Entities.Listings.Listing - a listing in
     */
    public static Listing createListing(String jsonDataString) throws IOException {
        try{
            JSONObject jsonData = new JSONObject(jsonDataString);

            ListingType type = ListingType.valueOf((String) jsonData.get("listingType"));
            switch(type){
                case CUSTOM:
                    return new CreateCustomListing().create(jsonData);
                case LINKED_IN:
                case INDEED:
                    throw new java.lang.UnsupportedOperationException();
                default:
                    throw new IOException();
            }
        }
        catch (JSONException e){
            throw new IOException(e);
        }
    }

    /**
     * Creates a string in JSON format that represents listing
     *
     * @param listing the listing to create a JSON Formatted string for
     * @return a string in JSON format represting the data of the listing
     */
    public static String createJSON(Listing listing){
        return listing.toJson().toString();
    }

    /**
     * verifies integrity of the JSON data.
     * @param jsonData
     * @return
     */
    private static boolean verifyListingIntegrity(JSONObject jsonData){
        String[] integrity = ("UID listingType title location pay jobType qualifications requirements " +
                "applicationReq description saved listingDate crossPlatformDuplicates").split(" ");
        Set<String> keys = jsonData.keySet();

        for (String s : integrity) {
            if (!keys.contains(s)) {
                return false;
                // throw new IOException("JSON data missing keys");
            }
        }
        return true;
    }

    public static ArrayList<Listing> loadListingsFromFileDirectory(String relPath){

        ArrayList<String> fileNames = FileIO.GetFileNamesInDir(relPath, ".json");
        ArrayList<Listing> listings = new ArrayList<>();

        for(String file : fileNames) {
            String jsonDataString = FileIO.ReadFile(relPath + file);
            try {
                Listing listing = DataFormat.createListing(jsonDataString);
                listings.add(listing);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return listings;
    }
}
