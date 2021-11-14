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
    protected void score_calculate() {
        double score = 0.0;
        ArrayList<Experience> user_experiences = (ArrayList<Experience>) this.user.getData(User.UREL_WORK_EXP);
        for(Experience experience : user_experiences) {
            HashMap<String, Object> experience_data = experience.serialize();
            LocalDate start_date = (LocalDate) experience_data.get(experience.START_TIME);
            LocalDate end_date = (LocalDate) experience_data.get(experience.END_TIME);
            String title = (String) experience_data.get(experience.EXPERIENCE_TITLE);
            String description = (String) experience_data.get(experience.EXPERIENCE_DESCRPTION);
            double experience_score = score_calc_time(start_date, end_date);
            score += experience_score * 0.5;
        }

        handler_main.score += score;
    }

}
