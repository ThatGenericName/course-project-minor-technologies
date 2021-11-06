package Controllers.DataProcessing;

import Entities.Listing.JobListing;
import Framework.FileIO.FileIO;
import UseCase.FileIO.IEntryDeserializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.FileIO.MalformedDataException;
import UseCase.Listing.ICreateListing;

import java.util.ArrayList;
import java.util.HashMap;

public class DataFormat {

    //TODO: make serializer type not hardcoded
    public static JobListing createListing(String dataString) throws MalformedDataException{

        IEntryDeserializer deserializer= new JSONSerializer();
        return createListing(dataString, deserializer);
    }

    public static JobListing createListing(String dataString, IEntryDeserializer deserializer) throws MalformedDataException {
        HashMap<String, Object> deserializedData = deserializer.Deserialize(dataString);

        return ICreateListing.createListing(deserializedData);
    }

    public static ArrayList<JobListing> loadListingsFromFileDirectory(String relPath){

        ArrayList<String> fileNames = FileIO.GetFileNamesInDir(relPath, ".json");
        ArrayList<JobListing> jobListings = new ArrayList<>();

        for(String file : fileNames) {
            String dataString = FileIO.ReadFile(relPath + file);
            try {
                JobListing jobListing = createListing(dataString);
                jobListings.add(jobListing);
            } catch (MalformedDataException e) {
                e.printStackTrace();
            }
        }
        return jobListings;
    }
}
