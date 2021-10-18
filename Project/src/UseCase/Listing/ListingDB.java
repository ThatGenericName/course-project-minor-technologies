package UseCase.Listing;

import Entities.Listing.Listing;
import Entities.Listing.ListingType;
import UseCase.IDatabase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ListingDB implements IDatabase, Map<ListingType, ArrayList<Listing>> {

    private final HashMap<ListingType, ArrayList<Listing>> listingDB;

    public ListingDB(ArrayList<Listing> listings){
        listingDB = new HashMap<>();

        for (Listing listing:
             listings) {
            addListing(listing);
        }
    }

    public ListingDB(){
        listingDB = new HashMap<>();
    }

    /**
     * Adds a listing to the database if the listing does not already exist in the database
     *
     * @param listing - The listing to be added to the database
     * @return true of the listing was successfully added to the database. False otherwise
     */
    public boolean addListing(Listing listing) {
        ListingType type = listing.getListingType();

        if (!listingDB.containsKey(type)){
            listingDB.put(type, new ArrayList<>());
        }

        if (getListingIndex(listing) != -1) {
            return false;
        } else {
            listingDB.get(type).add(listing);
            return true;
        }
    }

    /**
     * Update the listing by removing the old listing and replacing it with a new one.
     * returns true if listing was successfully updated.
     * returns false if no such listing had existed in the database.
     *
     * // This is done this way for thread safety. If listing was being updated as, for example, a driver was attempting
     * // to display it, it would cause some concurrentAccess related exceptions. Until I figure out how that can be
     * // correctly done, the listing would have to be replaced entirely.
     *
     * @param listing
     * @return
     */
    public boolean updateListing(Listing listing){
        int index = getListingIndex(listing);
        if (index != -1){
            ArrayList<Listing> db = listingDB.get(listing.getListingType());
            db.remove(index);
            db.add(listing);
            return true;
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
    private int getListingIndex(Listing listing){
        ListingType type = listing.getListingType();
        ArrayList<Listing> db = listingDB.get(type);
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUID() == listing.getUID()){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return listingDB.size();
    }

    @Override
    public boolean isEmpty() {
        return listingDB.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return listingDB.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return listingDB.containsValue(value);
    }

    @Override
    public ArrayList<Listing> get(Object key) {
        return listingDB.get(key);
    }

    @Nullable
    @Override
    public ArrayList<Listing> put(ListingType key, ArrayList<Listing> value) {
        return listingDB.put(key, value);
    }

    @Override
    public ArrayList<Listing> remove(Object key) {
        return listingDB.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends ListingType, ? extends ArrayList<Listing>> m) {
        listingDB.putAll(m);
    }

    @Override
    public void clear() {
        listingDB.clear();
    }

    @NotNull
    @Override
    public Set<ListingType> keySet() {
        return listingDB.keySet();
    }

    @NotNull
    @Override
    public Collection<ArrayList<Listing>> values() {
        return listingDB.values();
    }

    @NotNull
    @Override
    public Set<Entry<ListingType, ArrayList<Listing>>> entrySet() {
        return listingDB.entrySet();
    }
}
