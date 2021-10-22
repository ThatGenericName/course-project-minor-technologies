package UseCase.FileIO;

import java.util.HashMap;

public interface IEntryDeserializer {

    HashMap<String, Object> Deserialize(String data);
}
