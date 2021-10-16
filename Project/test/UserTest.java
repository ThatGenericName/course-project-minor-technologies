import org.junit.*;
import java.util.ArrayList;

import src.User;
import src.Listing;

class UserTest {
    User u1 = new User("Jack");
    Listing l1 = Listing("Software engineer", "Toronto", 100000, JobType jobType, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others");

    @Test
    public testAddListingToWatch() {
        u1.addListingToWatch(l1);
        ArrayList <Listing> test = new ArrayList<>();
        test.add(l1)

        assertEquals(u1.watchedListings, test);
    }
}