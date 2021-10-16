import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import src.LocalCache;
import src.CustomListing;
import src.ListingType;
import src.JobType;

public class LocalCacheTest {
    JobType j1 = JobType.FULL_TIME;
    LocalCache lc;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others");
    int u1 = l1.UID;

    @Before
    public setUp() {
        lc.listingsMap.put(l1.listingType, new ArrayList<>());
        lc.listingsMap.get(l1.listingType).add(l1)
    }

    @Test
    public testLoadSavedListings() {

    }

    @Test
    public testSaveAllListings() {

    }

    @Test
    public testLoadListingFromUID() {
        CustomListing l2 = new CustomListing("Data automator", "China", 99, j1, "College Degree",
                "1 year experience", "Resume and Cover letter", "Engineering in Python and others");
        l1.setTitle("Data automator");
        l1.setLocation("China");
        l1.setPay(99);
        lc.loadListingFromUID(u1);
        int u2 = lc.containsUID(CUSTOM, u1);
        assertEquals(lc.listingsMap.get(l1.listingType).get(u2), l2);
    }
}