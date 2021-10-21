package Controllers.BackgroundOperations;

import java.util.HashSet;
import java.util.List;

import Controllers.LocalCache.*;
import Entities.Listing.Listing;

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
        if (LocalCache.getCurrentActiveUser() != null){
            HashSet<Listing> watched = LocalCache.getCurrentActiveUser().getWatchedListings();
            for (Listing listing:
                    watched) {
                LocalCache.loadListingFromUID(listing.getUID());
            }
        }
    }
}
