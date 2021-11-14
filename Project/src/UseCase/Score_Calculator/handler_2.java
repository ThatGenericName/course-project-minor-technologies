package UseCase.Score_Calculator;

import Entities.Listing.JobListing;
import Entities.User.User;
import org.joda.time.Months;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class handler_2 extends handler_ext_work{


    public handler_2(User user_input, JobListing job_input) {
        super(user_input, job_input);
    }

    @Override
    protected void score_calculate() {
        double score = 0.0;
        LocalDate start_date = (LocalDate) this.user.getData(User.REL_WORK_EXP);
        LocalDate end_date = (LocalDate) this.user.getData(User.REL_WORK_EXP);
        String title = (String) this.user.getData(User.REL_WORK_EXP);
        ArrayList<String> description = (ArrayList<String>) this.user.getData(User.REL_WORK_EXP);
        double sub_score = score_calc_time(start_date, end_date);

        score = sub_score * description.size();
        handler_main.score += score;

        }

    }



