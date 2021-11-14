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
import static org.junit.Assert.*;


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

    @Test
    public void testHandler1ScoreCalculate() {
        handler_main handler_main1 = new handler_main(u1, jl1);
        handler_1 handler1 = new handler_1(u1, jl1);

        handler1.score_calculate();
        assertEquals(handler_main1.get_score(), 3.3);
    }

    @Test
    public void testHandler2ScoreCalculate() {
        handler_main handler_main2 = new handler_main(u1, jl1);
        handler_2 handler2 = new handler_2(u1, jl1);

        handler2.score_calculate();
        assertEquals(handler_main2.get_score(), 3.3);
    }

    @Test
    public void testHandler3ScoreCalculate() {
        handler_main handler_main3 = new handler_main(u1, jl1);
        handler_3 handler3 = new handler_3(u1, jl1);

        handler3.score_calculate();
        assertEquals(handler_main3.get_score(), 3.3);
    }



}
