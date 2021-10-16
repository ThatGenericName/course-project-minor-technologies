import java.io.IOException;
import java.time.*;
import java.util.*;
import org.json.JSONObject;
import org.json.*;
import org.junit.*;

import src.Listing;

public class ListingTest {
    Listing l1 = new Listing("Software engineer", "Toronto", 100000, JobType jobType, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others");
    JSONObject obj = new JSONObject();

    @Before
    public setUp() {
        obj.put("UID", l1.UID);
        obj.put("listingType", l1.listingType);
        obj.put("title", "Data Automator");
        obj.put("location", "China");
        obj.put("pay", "99");
        obj.put("jobType", l1.jobType);
        obj.put("qualifications", l1.qualifications);
        obj.put("requirements", "No Experience");
        obj.put("applicationReq", l1.applicationRequirements);
        obj.put("description", l1.description);
        obj.put("saved", l1.saved);
        obj.put("listingDate", l1.listingDate);
        obj.put("crossPlatformDuplicates", l1.getCPDIDs());
    }

    @Test
    public testFromJson() {
        l1.fromJson(obj);
    }

    @Test
    public testEquals() {

    }
}