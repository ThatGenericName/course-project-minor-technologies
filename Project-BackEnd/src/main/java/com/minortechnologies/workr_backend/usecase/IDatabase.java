package com.minortechnologies.workr_backend.usecase;

import com.minortechnologies.workr_backend.entities.Entry;

import java.util.ArrayList;
import java.util.Iterator;

public interface IDatabase extends Iterable<Entry> {

    boolean addEntry(Entry entry);

    ArrayList<Entry> addEntries(Iterable<Entry> entries);

    boolean removeEntry(Entry entry);

    boolean updateEntry(Entry entry);

    boolean contains(Entry entry);
    int size();

    Entry getEquivalent(Entry entry);

    Iterator<Entry> iterator();
}
