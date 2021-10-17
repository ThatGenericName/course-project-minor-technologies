import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class LocalCacheTest {
    JobType j1 = JobType.FULL_TIME;
    ListingType lt = ListingType.CUSTOM;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");
    int u1 = l1.getUID();
    int u2;

    @Before
    public void setUp() {
        LocalCache.listingsMap.put(l1.listingType, new ArrayList<>());
        LocalCache.listingsMap.get(l1.listingType).add(l1);
    }
}