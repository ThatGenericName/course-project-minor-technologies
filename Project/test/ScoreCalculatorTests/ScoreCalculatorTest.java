package ScoreCalculatorTests;

import Entities.User.User;
import Entities.Listing.JobListing;

import java.util.ArrayList;
import java.util.HashMap;


public class ScoreCalculatorTest {

    /** Creating a user for unit tests. */
    User user_test = new User();
    user_test.addData(User.ACCOUNT_NAME, "Peter");
    ArrayList<String> user_skills = new ArrayList<String>();
    user_skills.add("Python");
    user_skills.add("C++");
    user_test.addData(User.SKILL_SET, user_skills);




}
