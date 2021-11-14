package ScoreCalculatorTests;

import Entities.Listing.CustomJobListing;
import Entities.User.User;
import Entities.Listing.JobListing;
import Entities.User.Experience;
import java.time.LocalDate;
import UseCase.Score_Calculator.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;


public class ScoreCalculatorTest {

    User u1 = new User();
    CustomJobListing jl1 = new CustomJobListing("Analyst, Economic Advisory");

    @Before
    public void setUp() {
        User u1 = JobListingDemo.creatingUser();
        CustomJobListing j1 = JobListingDemo.creatingJobListing();
    }

    @Test
    /** Tests that score_calculate() in handler_1.java computes user's score properly.
     * */
    public void testHandlerMainScoreCalculate(){
        handler_main handler = new handler_main(u1,jl1);
        double test_score = handler.get_score();


        assertEquals(test_score, );
    }

    public void testHandler2ScoreCalculate() {

    }



}
