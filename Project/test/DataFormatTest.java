import org.junit.*;

import src.Listing;
import org.json.JSONObject;
import src.DataFormat;

public class DataFormatTest {
    Listing l1 = new Listing("Software engineer", "Toronto", 100000, JobType jobType, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others");
    String jsonTest = new String();
    JSONObject obj = new JSONObject();
    DataFormat df;

    @Before
    public setUp() {
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
    public testCreateListing() {
        assertEquals(df.createListing(obj.toString()), l1);
    }

    @Test
    public testCreateJSON() {
        assertEquals(df.createJSON(l1), obj.toString());
    }
}