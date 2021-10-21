package UseCase;

import Entities.IEntry;
import UseCase.Listing.ListingDB;

import java.util.Iterator;

public interface IDatabase extends Iterable<IEntry> {

    boolean addEntry(IEntry entry);

    boolean removeEntry(IEntry entry);

    boolean updateEntry(IEntry entry);

    boolean contains(IEntry entry);
    int size();

    Iterator<IEntry> iterator();
}
