package ScoreCalculatorTests;

import Entities.Listing.CustomJobListing;
import Entities.User.User;
import UseCase.Score_Calculator.*;

import org.junit.*;
import static org.junit.Assert.*;


public class ScoreCalculatorTest {

    User user1;
    CustomJobListing jobl1;

    @Before
    public void setUp() {
        user1 = JobListingDemo.creatingUser();
        jobl1 = JobListingDemo.creatingJobListing();
    }

    @Test
    /** Tests that score_calculate() in handler_1.java computes user's score properly.
     * */
    public void testHandlerMainScoreCalculate(){
        handler_main handler = new handler_main(user1,jobl1);
        handler.main();
        double test_score = handler.get_score();


//        assertEquals(test_score, 68.0, 0.01f);
    }

    @Test
    public void testHandler1ScoreCalculate() {
        handler_main handler_main1 = new handler_main(u1, jl1);
        handler_1 handler1 = new handler_1(u1, jl1);

        handler1.score_calculate();
        assertEquals(handler_main1.get_score(), 30.0,0.01);
    }

    @Test
    public void testHandler2ScoreCalculate() {
        handler_2 handler2 = new handler_2(user1, jobl1);

        handler2.score_calculate();
        assertEquals(handler_main2.get_score(), 32.0);
    }

    @Test
    public void testHandler3ScoreCalculate() {
        handler_3 handler3 = new handler_3(user1, jobl1);

        handler3.score_calculate();
        assertEquals(handler_main3.get_score(), 6.0);
    }
}
