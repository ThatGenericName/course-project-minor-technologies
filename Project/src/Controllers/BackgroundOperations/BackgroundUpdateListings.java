package Controllers.BackgroundOperations;

import java.util.HashSet;
import Controllers.LocalCache.*;

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
            HashSet<Integer> watched = LocalCache.getCurrentActiveUser().getWatchedListings();
            for (int UID:
                    watched) {
                LocalCache.loadListingFromUID(UID);
            }
        }
    }
}
