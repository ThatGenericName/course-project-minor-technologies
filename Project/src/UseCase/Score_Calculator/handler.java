package UseCase.Score_Calculator;


import Entities.Listing.JobListing;
import Entities.User.User;

abstract class handler {
    handler next;
    User user;
    JobListing job;
    double score;

    public handler(User user_input, JobListing job_input){
        this.next = null; this.user = user_input; this.job=job_input; this.score = 0.0d;
    }

    public void set_next(handler handler_input){
        this.next = handler_input;
    }

    public double get_score(){ return this.score;}

    void process_request(){
        score_calculate();
        if (next != null){
            next.process_request();this.score = this.score + next.get_score();}

    }

    public abstract void score_calculate();

}
