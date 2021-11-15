package UseCase.Score_Calculator;

import Entities.Listing.JobListing;
import Entities.User.User;
import Entities.User.Experience;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;

public class handler_3 extends handler_ext_work{


    public handler_3(User user_input, JobListing job_input) {
        super(user_input, job_input);
    }

    @Override
    public void score_calculate() {
        double score = 0.0;
        ArrayList<Experience> user_experiences = (ArrayList<Experience>) this.user.getData(User.UREL_WORK_EXP);
        if(user_experiences != null){
            for(Experience experience : user_experiences) {
                LocalDate start_date = (LocalDate) experience.getData(experience.START_TIME);
                LocalDate end_date = (LocalDate) experience.getData(experience.END_TIME);
                String title = (String) experience.getData(experience.EXPERIENCE_TITLE);
                ArrayList<String> description = (ArrayList<String>) experience.getData(experience.EXPERIENCE_DESCRPTION);
                double experience_score = score_calc_time(start_date, end_date);
                score += experience_score * description.size() * 0.5;
            }
        }


        this.score += score;
    }

}
