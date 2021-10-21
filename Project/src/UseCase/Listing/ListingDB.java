package UseCase.Listing;

import Entities.IEntry;
import Entities.Listing.Listing;
import Entities.Listing.ListingType;
import UseCase.IDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class ListingDB implements IDatabase{

    private final HashMap<ListingType, ArrayList<Listing>> listingDB;

    public ListingDB(ArrayList<Listing> listings){
        listingDB = new HashMap<>();

        for (Listing listing:
             listings) {
            addEntry(listing);
        }
    }

    public ListingDB(){
        listingDB = new HashMap<>();
    }

    /**
     * Adds a listing to the database if the listing does not already exist in the database
     *
     * @param entry - The entry to be added to the database
     * @return true of the listing was successfully added to the database. False otherwise
     */
    public boolean addEntry(IEntry entry) {
        if (entry instanceof Listing){
            Listing listing = (Listing) entry;
            ListingType type = listing.getListingType();

            if (!listingDB.containsKey(type)){
                listingDB.put(type, new ArrayList<>());
            }

            if (getIndex(listing) == -1) {
                listingDB.get(type).add(listing);
                return true;
            }
        }
        return false;
    }

    /**
     * Update the listing by removing the old listing and replacing it with a new one. If no old listing exists, it
     * simply adds the listing to the database;
     * returns true if listing was successfully updated.
     * returns false if listing was not updated for any reason.
     *
     * // This is done this way for thread safety. If listing was being updated as, for example, a driver was attempting
     * // to display it, it would cause some concurrentAccess related exceptions. Until I figure out how that can be
     * // correctly done, the listing would have to be replaced entirely.
     *
     * @param entry the entry to be added
     * @return
     */
    @Override
    public boolean updateEntry(IEntry entry){
        if (entry instanceof Listing){
            Listing listing = (Listing) entry;
            ArrayList<Listing> db = listingDB.get(listing.getListingType());
            int index = getIndex(listing);
            if (index != -1){
                db.remove(index);
            }
            db.add(listing);
            return true;
        }
        return false;
    }

    /**
     * returns true if an Entry is contained in this database, otherwise returns false.
     *
     * @param entry
     * @return
     */
    @Override
    public boolean contains(IEntry entry) {
        if (entry instanceof Listing){
            return (getIndex((Listing) entry) != -1);
        }
        return false;
    }

    // TODO: complete docstring
    /**
     * returns the index for a listing with a matching UID;
     * returns -1 if there are no listings with the matching UID;
     *
     * @param listing
     * @return
     */
    private int getIndex(Listing listing){
        ListingType type = listing.getListingType();
        ArrayList<Listing> db = listingDB.get(type);
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUID() == listing.getUID()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes the provided entry from the database.
     *
     *
     * @param entry
     * @return
     */
    @Override
    public boolean removeEntry(IEntry entry) {
        if (entry instanceof Listing){
            Listing listing = (Listing) entry;
            int index = getIndex(listing);
            if (index != -1){
                listingDB.get(listing.getListingType()).remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {

        int size = 0;
        for (ListingType key:
                listingDB.keySet()) {
            size += listingDB.get(key).size();
        }
        return size;
    }


    @NotNull
    @Override
    public Iterator<IEntry> iterator() {
        return new ListingDBIterator(this.listingDB);
    }


    static class ListingDBIterator implements Iterator<IEntry>{

        private final Iterator<Listing> toIterate;

        public ListingDBIterator(HashMap<ListingType, ArrayList<Listing>> listingMap){
            ArrayList<Listing> totalList = new ArrayList<>();
            for (ListingType key:
                 listingMap.keySet()) {
                totalList.addAll(listingMap.get(key));
            }
            toIterate = totalList.iterator();
        }

        @Override
        public boolean hasNext() {
            return toIterate.hasNext();
        }

        @Override
        public IEntry next() {
            return toIterate.next();
        }
    }
}
