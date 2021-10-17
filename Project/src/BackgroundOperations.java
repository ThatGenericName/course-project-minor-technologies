import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class BackgroundOperations {

    public static ArrayList<Thread> threads = new ArrayList<>();

    private static int updateInterval = 5000;

    public static boolean isRunBackgroundOps() {
        return runBackgroundOps;
    }

    private static boolean runBackgroundOps = true;

    public static int getUpdateInterval() {
        return updateInterval;
    }

    public static void setUpdateInterval(int updateInterval) {
        BackgroundOperations.updateInterval = updateInterval;
    }

    /**
     * A loop that runs the background operations for this program such as automatic refreshing/updating
     * of the listings that a user watches.
     *
     * Ends existing threads if there are any threads running.
     *
     * This loop will run on a second thread.
     *
     */
    public static void startBackgroundLoop(){

        if (threads.size() > 0){
            endBackgroundThreads();
        }
        BackgroundUpdateListings thread = new BackgroundUpdateListings();
        threads.add(thread);

        thread.start();
    }

    public static void endBackgroundThreads(){
        runBackgroundOps = false;

        while (threads.size() > 0) {
            Thread thread = threads.get(threads.size() - 1);
            threads.remove(threads.size() - 1);

            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }
    }
}

// TODO: replace test code with actual code
class BackgroundUpdateListings extends Thread {
    public BackgroundUpdateListings(){
        super();
    }

    @Override
    public void run(){

        while (BackgroundOperations.isRunBackgroundOps()){
            try {
                updateListings();
                // updateListingsTest();
                sleep(BackgroundOperations.getUpdateInterval());
            } catch (InterruptedException e) {
                updateListings();
                // updateListingsTest();
            }
        }
    }

    private void updateListings(){
        if (Main.user != null){
            HashSet<Integer> watched = Main.user.getWatchedListings();
            for (int UID:
                    watched) {
                LocalCache.loadListingFromUID(UID);
            }
        }
    }
}
