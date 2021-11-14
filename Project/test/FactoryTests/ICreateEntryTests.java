package FactoryTests;

import Framework.FileIO.FileIO;
import UseCase.FileIO.IEntryDeserializer;
import UseCase.FileIO.JSONSerializer;
import org.junit.Before;

import java.io.File;
import java.util.HashMap;

public class ICreateEntryTests {


    /**
     * These tests assume that the FileIO and DataFormat has been correctly implemented.
     *
     */

    HashMap<String, Object> jobListingData;
    HashMap<String, Object> userData;
    HashMap<String, Object> searchQuery;
    String sep = File.separator;

    @Before
    public void setUp(){
        String testFiles = "Project" + sep + "test" + sep + "FactoryTests" + sep;
        IEntryDeserializer deserializer = new JSONSerializer();
        testFiles = FileIO.WORK_PATH.endsWith(sep) ? FileIO.WORK_PATH + testFiles : FileIO.WORK_PATH + sep + testFiles;
        String jlString = FileIO.readFile(testFiles + "Demo1.json");
        String userString = FileIO.readFile(testFiles + "User1.json");
        jobListingData = deserializer.deserialize(jlString);
        userData = deserializer.deserialize(userString);
    }

    @Override
    public void correctSubclassCreation(){

    }
}
