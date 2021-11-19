package com.minortechnologies.workr.Controllers.BackgroundOperations;

import java.util.HashSet;
import java.util.Map;

import com.minortechnologies.workr.Controllers.LocalCache.*;
import com.minortechnologies.workr.Controllers.UserManagement.UserManagement;
import com.minortechnologies.workr.Demo.DemoSource.DemoSourceJobListing;
import com.minortechnologies.workr.Demo.TotalDemo;
import com.minortechnologies.workr.Entities.Entry;
import com.minortechnologies.workr.Entities.Listing.JobListing;
import com.minortechnologies.workr.Main.Main;
import com.minortechnologies.workr.UseCase.Factories.ICreateEntry;
import com.minortechnologies.workr.UseCase.FileIO.MalformedDataException;

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
