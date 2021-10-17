import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DataFormatTest {
    JobType j1 = JobType.FULL_TIME;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");
    String jsonTest;
    JSONObject obj = new JSONObject();

    @Before
    public void setUp() {
        obj.put("UID", l1.getUID());
        obj.put("listingType", ListingType.CUSTOM);
        obj.put("title", l1.getTitle());
        obj.put("location", l1.getLocation());
        obj.put("pay", l1.getPay());
        obj.put("jobType", l1.getJobType());
        obj.put("qualifications", l1.getQualifications());
        obj.put("requirements", l1.getRequirements());
        obj.put("applicationReq", l1.getApplicationRequirements());
        obj.put("description", l1.getDescription());
        obj.put("saved", false);
        obj.put("listingDate", l1.getListingDate());
        obj.put("crossPlatformDuplicates", l1.getCPDIDs());
    }

    @Test
    public void testCreateListing() {
        try{
            assertEquals(DataFormat.createListing(obj.toString()), l1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateJSON() {
        assertEquals(DataFormat.createJSON(l1), obj.toString());
    }
}