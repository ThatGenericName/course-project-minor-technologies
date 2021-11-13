package UseCase.Score_Calculator;


import Entities.Listing.JobListing;
import Entities.User.User;

abstract class handler {
    handler next;
    User user;
    JobListing job;

    public handler(User user_input, JobListing job_input){
        this.next = null; this.user = user_input; this.job=job_input;
    }

    public void set_next(handler handler_input){
        this.next = handler_input;
    }

    void process_request(){
        score_calculate();
        if (next != null)
            next.process_request();
    }

    protected abstract void score_calculate();

}
