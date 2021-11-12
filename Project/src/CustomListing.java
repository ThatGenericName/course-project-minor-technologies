import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Set;

public class CustomListing extends Listing {

    private String origin;


    public CustomListing(JSONObject jsonData) throws IOException {
        super(jsonData);
        if (!fromJson(jsonData)){
            throw new IOException("JSON Data missing keys");
        }
    }

    public CustomListing(String title, String location, int pay, JobType jobType, String qualifications,
                         String requirements, String applicationRequirements, String description, String origin){
        super(title, location, pay, jobType, qualifications, requirements, applicationRequirements, description);

        this.listingType = ListingType.CUSTOM;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public JSONObject toJson() {

        JSONObject jsonData = super.toJson();

        jsonData.put("origin", origin);

        return jsonData;
    }

    /**
     * Loads JSON data into the object. Returns true if successfully loaded, otherwise returns false.
     *
     *
     * @param jsonData - a JSON Object representing the data of a listing. From JSON object
     * @return boolean - true if successfully loaded, otherwise returns false.
     */

    @Override
    public boolean fromJson(JSONObject jsonData) throws IOException {
        super.fromJson(jsonData);
        Set<String> keys = jsonData.keySet();

        if (keys.contains("origin")){
            this.origin = (String) jsonData.get("origin");
            return true;
        }
        else{
            return false;
        }
    }
}
