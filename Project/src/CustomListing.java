import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CustomListing extends Listing {

    private String origin;


    public CustomListing(JSONObject jsonData) throws IOException {
        super(jsonData);
        fromJson(jsonData);
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

    @Override
    public boolean fromJson(JSONObject jsonData) throws IOException {
        try{
            super.fromJson(jsonData);
            this.origin = (String) jsonData.get("origin");
            return true;
        }
        catch (JSONException e){
            throw new IOException(e);
        }
    }
}
