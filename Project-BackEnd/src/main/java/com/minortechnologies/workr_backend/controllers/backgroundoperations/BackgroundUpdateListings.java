package com.minortechnologies.workr_backend.controllers.backgroundoperations;

import java.util.HashSet;
import java.util.Map;

import com.minortechnologies.workr_frontend.controllers.localcache.*;
import com.minortechnologies.workr_backend.controllers.usermanagement.UserManagement;
import com.minortechnologies.workr_backend.demo.demosource.DemoSourceJobListing;
import com.minortechnologies.workr_backend.demo.TotalDemo;
import com.minortechnologies.workr_backend.entities.Entry;
import com.minortechnologies.workr_backend.entities.listing.JobListing;
import com.minortechnologies.workr_backend.main.Main;
import com.minortechnologies.workr_backend.usecase.factories.ICreateEntry;
import com.minortechnologies.workr_backend.usecase.fileio.MalformedDataException;
import com.minortechnologies.workr_backend.controllers.localcache.LocalCache;

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
