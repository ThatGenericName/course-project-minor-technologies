package Controllers.BackgroundOperations;

import java.util.HashSet;

import Controllers.LocalCache.*;
import Controllers.UserManagement.UserManagement;
import Entities.Listing.JobListing;
import Main.Main;

public class BackgroundUpdateListings implements IBackgroundOperation{
    @Override
    public void run() {
        while (BackgroundOperations.isRunBackgroundOps()){
            try {
                updateListings();
                // updateListingsTest();
                Thread.sleep(BackgroundOperations.getUpdateInterval());
            } catch (InterruptedException e) {
                updateListings();
                // updateListingsTest();
            }
        }
    }

    private void updateListings(){
        UserManagement um = Main.getUserManagement();
        LocalCache lm = Main.getLocalCache();
        if (Main.getUserManagement().getCurrentActiveUser() != null){
            HashSet<JobListing> watched = um.getCurrentActiveUser().getWatchedListings();
            for (JobListing jobListing :
                    watched) {
                lm.loadListingFromUUID(jobListing.getUUID());
            }
        }
    }
}
