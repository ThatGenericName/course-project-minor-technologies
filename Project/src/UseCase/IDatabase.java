package UseCase;

import Entities.Entry;

import java.util.Iterator;

public interface IDatabase extends Iterable<Entry> {

    boolean addEntry(Entry entry);

    boolean removeEntry(Entry entry);

    boolean updateEntry(Entry entry);

    boolean contains(Entry entry);
    int size();

    Iterator<Entry> iterator();
}
