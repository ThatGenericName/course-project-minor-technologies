import java.util.*;

import Controllers.LocalCache.LocalCache;
import Entities.Listing.CustomListing;
import Entities.Listing.CustomListingBuilder;
import Entities.Listing.JobType;
import Entities.Listing.ListingType;
import org.junit.*;

public class LocalCacheTest {
    JobType j1 = JobType.FULL_TIME;
    ListingType lt = ListingType.CUSTOM;
    CustomListing l1 = new CustomListingBuilder().setTitle("Software engineer").setLocation("Toronto").setPay(100000).setJobType(j1).setQualifications("College Degree").setRequirements("1 year experience").setApplicationRequirements("Resume and Cover letter").setDescription("Engineering in Python and others").setOrigin("LinkedIn").createCustomListing();
    int u1 = l1.getUID();
    int u2;

    @Before
    public void setUp() {
        LocalCache.listingDB.put(l1.getListingType(), new ArrayList<>());
        LocalCache.listingDB.get(l1.getListingType()).add(l1);
    }
}