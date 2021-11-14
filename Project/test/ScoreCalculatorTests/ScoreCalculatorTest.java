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

    /** Used this job listing on LinkedIn to create user and job listing for unit tests:
     * https://www.linkedin.com/jobs/view/2727426318
     */

    /** Creating a user for unit tests. */
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
    public void testHandler1ScoreCalculate(){
        handler_1 handler1 = new handler_1(u1,jl1);
        double test_score_h1 = handler1.score_calculate();

        assertEquals(test_score_h1, );
    }

    public void testHandler2ScoreCalculate() {

    }



}
