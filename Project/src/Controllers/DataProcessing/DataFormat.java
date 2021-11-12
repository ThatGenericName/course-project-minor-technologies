package Controllers.DataProcessing;

import Entities.Entry;
import Framework.FileIO.FileIO;
import UseCase.FileIO.IEntryDeserializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.FileIO.MalformedDataException;
import UseCase.Factories.ICreateEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class DataFormat {

    //TODO: make serializer type not hardcoded
    public static Entry createEntry(String dataString) throws MalformedDataException{

        IEntryDeserializer deserializer= new JSONSerializer();
        return createEntry(dataString, deserializer);
    }

    public static Entry createEntry(String dataString, IEntryDeserializer deserializer) throws MalformedDataException {
        HashMap<String, Object> deserializedData = deserializer.Deserialize(dataString);

        return ICreateEntry.createEntry(deserializedData);
    }

    public static ArrayList<Entry> loadEntriesFromDirectory(String relPath){

        ArrayList<String> fileNames = FileIO.getFileNamesInDir(relPath, ".json");
        ArrayList<Entry> entries = new ArrayList<>();

        if (!relPath.endsWith(File.separator)){
            relPath = relPath + File.separator;
        }

        for(String file : fileNames) {
            String dataString = FileIO.readFile(relPath + file);
            try {
//                JobListing jobListing = createListing(dataString);
//                jobListings.add(jobListing);
                Entry entry = createEntry(dataString);
                entries.add(entry);
            } catch (MalformedDataException e) {
                e.printStackTrace();
            }
        }
        return entries;
    }

    /**
     * Loads all entry files from each directory in relDir.
     * TODO: finish Docstring
     * @param relDir
     * @return
     */
    public static ArrayList<Entry> loadEntiresFromDirectorySub(String relDir){
        ArrayList<String> directories = FileIO.getDirectoryNamesInDir(relDir);
        ArrayList<Entry> entries = new ArrayList<>();
        for (String directoryName:
                directories) {
            String path = relDir.endsWith(File.separator) ? relDir + directoryName : relDir + File.separator + directoryName;
            ArrayList<String> files = FileIO.getFileNamesInDir(path, ".json");
            for (String file:
                    files) {
                if (file.startsWith("entry_")){
                    String data = FileIO.readFile(path + File.separator + file);
                    Entry entry = null;
                    try {
                        entry = createEntry(data);
                    } catch (MalformedDataException e) {
                        e.printStackTrace();
                    }
                    entries.add(entry);
                }
            }
        }
        return entries;
    }
}
