package ScoreCalculatorTests;

import Entities.User.User;
import Entities.Listing.JobListing;
import Entities.User.Experience;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;


public class ScoreCalculatorTest {

    /** Used this job listing on LinkedIn to create user and job listing for unit tests:
     * https://www.linkedin.com/jobs/view/2727426318
     */

    /** Creating a user for unit tests. */

    void CreatingUser(){
        User user_test = new User();
        user_test.addData(User.ACCOUNT_NAME, "Peter");
        ArrayList<String> user_skills = new ArrayList<String>();
        user_skills.add("STATA");
        user_skills.add("R");
        user_skills.add("MS Excel");
        user_test.addData(User.SKILL_SET, user_skills);
        Experience experience1 = new Experience();
        experience1.addData(Experience.EXPERIENCE_TITLE, "Experience in economic consulting");
        ArrayList<String> experience1_description = new ArrayList<String>();
        experience1_description.add("Knowledge of basic economic principles");
        experience1_description.add("Ability to conduct independent economic research");
        experience1.addData(Experience.EXPERIENCE_DESCRPTION, experience1_description);
        LocalDate start_date = LocalDate.of(2020,1,20);
        LocalDate end_date = LocalDate.of(2021, 10, 20);
        experience1.addData(Experience.START_TIME, start_date);
        experience1.addData(Experience.END_TIME, end_date);
        Experience experience2 = new Experience();
        experience2.addData(Experience.EXPERIENCE_TITLE, "Experience in data analysis");
        ArrayList<String> experience2_description = new ArrayList<String>();
        experience2_description.add("Experience working with public data sources");
        experience2.addData(Experience.EXPERIENCE_DESCRPTION, experience2_description);
        LocalDate start_date1 = LocalDate.of(2021,1,20);
        LocalDate end_date1 = LocalDate.of(2021, 10, 20);
        experience2.addData(Experience.START_TIME, start_date1);
        experience2.addData(Experience.END_TIME, end_date1);
        Experience unrelated_experience = new Experience();
        unrelated_experience.addData(Experience.EXPERIENCE_TITLE, "Experience working with students");
        ArrayList<String> unrelated_description = new ArrayList<String>();
        unrelated_description.add("Worked as a math tutor at a high school");
        unrelated_experience.addData(Experience.EXPERIENCE_DESCRPTION,unrelated_description);
        LocalDate start_date2 = LocalDate.of(2019,1,20);
        LocalDate end_date2 = LocalDate.of(2021, 10, 20);
        unrelated_experience.addData(Experience.START_TIME, start_date2);
        unrelated_experience.addData(Experience.END_TIME, end_date2);
    }





}
