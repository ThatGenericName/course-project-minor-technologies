import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataFormatTest {
    JobType j1 = JobType.FULL_TIME;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");
    String jsonTest;
    JSONObject obj = new JSONObject();

    @Before
    public void setUp() {
        obj.put("UID", l1.UID);
        obj.put("listingType", l1.listingType);
        obj.put("title", l1.title);
        obj.put("location", l1.location);
        obj.put("pay", l1.pay);
        obj.put("jobType", l1.jobType);
        obj.put("qualifications", l1.qualifications);
        obj.put("requirements", l1.requirements);
        obj.put("applicationReq", l1.applicationRequirements);
        obj.put("description", l1.description);
        obj.put("saved", l1.saved);
        obj.put("listingDate", l1.listingDate);
        obj.put("crossPlatformDuplicates", l1.getCPDIDs());
    }

    @Test
    public void testCreateListing() {
        assertEquals(DataFormat.createListing(obj.toString()), l1);
    }

    @Test
    public void testCreateJSON() {
        assertEquals(DataFormat.createJSON(l1), obj.toString());
    }
}