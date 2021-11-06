package Controllers.BackgroundOperations;

import java.util.HashSet;

import Controllers.LocalCache.*;
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
        LocalCache lc = Main.getLocalCache();
        if (Main.getLocalCache().getCurrentActiveUser() != null){
            HashSet<JobListing> watched = lc.getCurrentActiveUser().getWatchedListings();
            for (JobListing jobListing :
                    watched) {
                lc.loadListingFromUUID(jobListing.getUUID());
            }
        }
    }
}
