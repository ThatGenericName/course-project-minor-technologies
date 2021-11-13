package Controllers.BackgroundOperations;

import java.util.HashSet;
import java.util.Map;

import Controllers.LocalCache.*;
import Controllers.UserManagement.UserManagement;
import Demo.DemoSource.DemoJobListingSource;
import Demo.DemoSource.DemoSourceJobListing;
import Demo.TotalDemo;
import Entities.Entry;
import Entities.Listing.JobListing;
import Main.Main;
import UseCase.Factories.ICreateEntry;
import UseCase.FileIO.MalformedDataException;

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
                switch (jobListing.getListingType()){
                    case DEMO_SOURCE:
                        updateDemoSourceListing(jobListing);
                        break;
                    default:
                        lm.updateEntryByUUID(jobListing.getUUID());
                        break;
                }

            }
        }
    }

    private void updateDemoSourceListing(JobListing listing){

        if (TotalDemo.getInitiated()){

            String dsid = (String) listing.getData(DemoSourceJobListing.DEMO_SOURCE_ID);
            Map<String, Object> newEntryData = TotalDemo.demoSource.getDemoListingByDSID(dsid);

            try {
                Entry newEntry = ICreateEntry.createEntry(newEntryData);
                listing.updateEntry(newEntry);
            } catch (MalformedDataException e) {
                e.printStackTrace();
            }
        }
    }
}
