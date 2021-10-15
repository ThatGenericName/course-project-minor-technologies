import java.util.ArrayList;
import java.util.HashSet;

public class BackgroundOperations {

    public static User user = null;
    public static ArrayList<Thread> threads = new ArrayList<>();

    public static boolean isRunBackgroundOps() {
        return runBackgroundOps;
    }

    private static boolean runBackgroundOps = true;

    /**
     * A loop that runs the background operations for this program such as automatic refreshing/updating
     * of the listings that a user watches.
     *
     * This loop will run on a second thread.
     *
     */
    public static void BackgroundLoop(){

        BackgroundUpdateListings thread = new BackgroundUpdateListings();
        threads.add(thread);

        thread.start();
    }

    public static void EndBackgroundThreads(){
        runBackgroundOps = false;
        for (Thread thread:
             threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }
    }
}


class BackgroundUpdateListings extends Thread {
    public BackgroundUpdateListings(){
        super();
    }

    @Override
    public void run(){

        while (BackgroundOperations.isRunBackgroundOps()){
            try {
                updateListings();
                sleep(10000);
            } catch (InterruptedException e) {
                updateListings();
            }
        }
    }

    private void updateListings(){
        HashSet<Listing> watched = Main.user.getWatchedListings();
        for (Listing listing:
                watched) {
            int UID = listing.getUID();

            LocalCache.loadListingFromUID(UID);
        }
    }
}
