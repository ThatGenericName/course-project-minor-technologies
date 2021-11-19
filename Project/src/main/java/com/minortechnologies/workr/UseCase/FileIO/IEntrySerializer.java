package com.minortechnologies.workr.UseCase.FileIO;

import java.util.HashMap;

public interface IEntrySerializer {

    String serialize(HashMap<String, Object> serializedHashmap);

    String serializerExtension();
}
