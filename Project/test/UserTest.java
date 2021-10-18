import Entities.Listing.CustomListing;
import Entities.Listing.CustomListingBuilder;
import Entities.Listing.JobType;
import Entities.User.User;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashSet;

public class UserTest {
    User u1 = new User("Jack");
    JobType j1 = JobType.FULL_TIME;
    CustomListing l1 = new CustomListingBuilder().setTitle("Software engineer").setLocation("Toronto").setPay(100000).setJobType(j1).setQualifications("College Degree").setRequirements("1 year experience").setApplicationRequirements("Resume and Cover letter").setDescription("Engineering in Python and others").setOrigin("LinkedIn").createCustomListing();

    @Test
    public void testAddListingToWatch() {
        u1.addListingToWatch(l1);
        HashSet<Integer> test = new HashSet<>();
        test.add(l1.getUID());
        assertEquals(u1.getWatchedListings(), test);
    }
}