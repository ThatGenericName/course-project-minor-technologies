import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DataFormat {

    /**
     * Creates a Listing from the jsonDataString
     * @param jsonDataString - String representing the listing data in JSON format
     * @return Listing - a listing in
     */
    public static Listing createListing(String jsonDataString) throws IOException {
        try{
            JSONObject jsonData = new JSONObject(jsonDataString);
            ListingType type = ListingType.valueOf((String) jsonData.get("listingType"));
//            switch(type){
//                case (CUSTOM):
//                    return new CustomListing(jsonData);
//                    break;
//                default:
//                    throw new IOException();
//            }

            // for some reason, Intellij/java thinks enums are not constants, which means until I can figure out why,
            // this will have to be an ugly else if chain.
            if (type == ListingType.CUSTOM){
                return new CustomListing(jsonData);
            }
            else if (type == ListingType.INDEED){
                throw new java.lang.UnsupportedOperationException();
            }
            else if (type == ListingType.LINKED_IN){
                throw new java.lang.UnsupportedOperationException();
            }
            else{
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
}
