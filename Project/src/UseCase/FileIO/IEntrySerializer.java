package UseCase.FileIO;

import Entities.IEntry;

import java.util.HashMap;

public interface IEntrySerializer {

    String serialize(HashMap<String, Object> serializedHashmap);
}
