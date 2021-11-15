package UseCase.Score_Calculator;

import Entities.Listing.JobListing;
import Entities.User.User;

import java.time.LocalDate;
import java.time.Period;

public class handler_main{
    protected User user;
    protected JobListing job;
    double score;

    public handler_main(User user_input, JobListing job_input) {
        user = user_input;
        job = job_input;
        this.score=0.0;
    }
    public double get_score(){return this.score;}

    public void main(){
        handler h1 = new handler_1(this.user, this.job);
        handler h2 = new handler_2(this.user, this.job);
        handler h3 = new handler_3(this.user, this.job);
        h1.set_next(h2);
        h2.set_next(h3);
        h1.process_request();
        this.score = h1.get_score();
    }

}
