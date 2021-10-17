import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;

public class UserTest {
    User u1 = new User("Jack");
    JobType j1 = JobType.FULL_TIME;
    CustomListing l1 = new CustomListing("Software engineer", "Toronto", 100000, j1, "College Degree",
            "1 year experience", "Resume and Cover letter", "Engineering in Python and others", "LinkedIn");

    @Test
    public void testAddListingToWatch() {
        u1.addListingToWatch(l1);
        HashSet<Integer> test = new HashSet<>();
        test.add(l1.getUID());
        assertEquals(u1.getWatchedListings(), test);
    }
}