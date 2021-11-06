package UseCase.Listing;

import Entities.Listing.CustomJobListing;
import Entities.Listing.JobListing;
import UseCase.FileIO.MalformedDataException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CreateCustomListing implements ICreateListing {

    public CreateCustomListing(){
    }

    /**
     * Creates a CustomListing from a JSONData.
     *
     *
     * @param listingJsonData - a JSONObject containing the data for a CustomListing
     * @return A CustomListing from the listingJsonData
     * @throws IOException if the JSONData is missing keys required for this listing type
     */
    @Override
    public JobListing create(Map<String, Object> listingJsonData) throws MalformedDataException {
        ArrayList<String> missingKeys = verifyMapIntegrity(listingJsonData);
        if (missingKeys.size() == 0){
            return new CustomJobListing(listingJsonData);
        }
        else{
            throw new MalformedDataException(ICreateListing.missingKeyInfo(missingKeys, "CUSTOM"));
        }
    }

    @Override
    public @NotNull ArrayList<String> verifyMapIntegrity(Map<String, Object> listingJsonData){
        ArrayList<String> missingKeys = new ArrayList<>();
        Set<String> keys = listingJsonData.keySet();

        if (!keys.contains("origin")){
            missingKeys.add("origin");
        }
        return missingKeys;
    }
}
