package Controllers.BackgroundOperations;

import java.util.HashSet;
import java.util.List;

import Controllers.LocalCache.*;
import Entities.Listing.Listing;
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
            HashSet<Listing> watched = lc.getCurrentActiveUser().getWatchedListings();
            for (Listing listing:
                    watched) {
                lc.loadListingFromUID(listing.getUID());
            }
        }
    }
}
