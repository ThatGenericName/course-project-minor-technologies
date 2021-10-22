package Entities;

import java.util.HashMap;

public interface IEntry {

    HashMap<String, Object> serialize();

    String getSerializedFileName();
}
