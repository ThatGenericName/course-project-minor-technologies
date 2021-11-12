import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DataFormatTest {
    JobType j1 = JobType.FULL_TIME;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");
    JSONObject jsonObj = new JSONObject();

    @Before
    public void setUp() {
        jsonObj.put("UID", l1.getUID());
        jsonObj.put("listingType", ListingType.CUSTOM);
        jsonObj.put("title", l1.getTitle());
        jsonObj.put("location", l1.getLocation());
        jsonObj.put("pay", l1.getPay());
        jsonObj.put("jobType", l1.getJobType());
        jsonObj.put("qualifications", l1.getQualifications());
        jsonObj.put("requirements", l1.getRequirements());
        jsonObj.put("applicationReq", l1.getApplicationRequirements());
        jsonObj.put("description", l1.getDescription());
        jsonObj.put("saved", false);
        jsonObj.put("listingDate", l1.getListingDate());
        jsonObj.put("crossPlatformDuplicates", l1.getCPDIDs());
        jsonObj.put("origin", l1.getOrigin());
    }

    @Test
    public void testCreateListing() {
        try{
            Listing listing = DataFormat.createListing(jsonObj.toString());
            assertEquals(listing, l1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}