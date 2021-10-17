import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class LocalCacheTest {
    JobType j1 = JobType.FULL_TIME;
    ListingType lt = ListingType.CUSTOM;
    LocalCache lc;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");
    int u1 = l1.getUID();
    int u2;

    @Before
    public void setUp() {
        LocalCache.listingsMap.put(l1.listingType, new ArrayList<>());
        LocalCache.listingsMap.get(l1.listingType).add(l1);
    }

    @Test
    public void testLoadSavedListings() {

    }

    @Test
    public void testSaveAllListings() {

    }

    @Test
    public void testLoadListingFromUID() {
        CustomListing l2 = new CustomListing("Data automator", "China", 99, j1, "College Degree",
                "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");
        l1.setTitle("Data automator");
        l1.setLocation("China");
        l1.setPay(99);
        LocalCache.loadListingFromUID(u1);
        for (int i = 0; i < LocalCache.listingsMap.get(lt).size(); i++) {
            if (LocalCache.listingsMap.get(lt).get(i).getUID() == l2.getUID()){
                u2 = i;
            }
        }
        assertEquals(LocalCache.listingsMap.get(l1.listingType).get(u2), l2);
    }
}