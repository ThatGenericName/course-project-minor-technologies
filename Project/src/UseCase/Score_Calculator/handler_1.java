package UseCase.Score_Calculator;

import Entities.Listing.JobListing;
import Entities.User.User;

import java.util.ArrayList;

public class handler_1 extends handler{


    public handler_1(User user_input, JobListing job_input) {
        super(user_input, job_input);
    }

    @Override
    public void score_calculate() {
        double score = 0.0;
        ArrayList<String> data = (ArrayList<String>) this.user.getData(User.SKILL_SET);
        for (String word : data) {
            if (!this.job.getData(JobListing.QUALIFICATIONS).equals("") &&
                    this.job.getData(JobListing.QUALIFICATIONS).toString().toLowerCase().contains(word.toLowerCase()))
                score += 10;
            else if (!this.job.getData(JobListing.REQUIREMENTS).equals("") &&
                    this.job.getData(JobListing.REQUIREMENTS).toString().toLowerCase().contains(word.toLowerCase()))
                score += 10;
            else if (!this.job.getData(JobListing.APPLICATION_REQUIREMENTS).equals("") &&
                    this.job.getData(JobListing.APPLICATION_REQUIREMENTS).toString().toLowerCase().contains(word.toLowerCase()))
                score += 10;
            else if (!this.job.getData(JobListing.DESCRIPTION).equals("") &&
                    this.job.getData(JobListing.DESCRIPTION).toString().toLowerCase().contains(word.toLowerCase()))
                score += 10;
            handler_main.score += score;

        }

    }
}
