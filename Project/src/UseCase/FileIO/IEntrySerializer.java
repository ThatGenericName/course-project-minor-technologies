package UseCase.FileIO;

import Entities.IEntry;

public interface IEntrySerializer {

    String[] serialize(IEntry entry);
}
