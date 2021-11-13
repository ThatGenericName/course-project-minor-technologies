package UseCase.FileIO;

import Entities.Listing.JobListing;
import Entities.Listing.JobType;
import Entities.Listing.ListingType;
import Entities.SearchQuery.SearchQuery;
import Entities.User.Experience;
import Entities.User.User;
import UseCase.Factories.ICreateEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class takes a HashMap and correctly casts the data to the correct types.
 *
 * This is used so that Entry.Deserialize(Entry.Serialize) works, which before this class
 * was created was not the case, as when deserializing, most of the data will be Strings and
 * not be parsed into a correct type, such as LocalDateTimes or Enums.
 *
 * Essentially, the type casting and string-data parsing was offloaded from the Entry subclass
 * to this new class.
 *
 */
public class EntryDataMapTypeCaster {

    //TODO: Maybe replaces these with hashsets to remove duplicates.

    public static final String[] DATE_TIME_KEYS = new String[] {JobListing.LISTING_DATE, Experience.START_TIME,
            Experience.END_TIME, SearchQuery.DATE_TIME};
    public static final String[] STRING_ARRAYS = new String[] {JobListing.CROSS_PLATFORM_DUPLICATES};

    public static final String[] ENUMS = new String[] {JobListing.JOB_TYPE, JobListing.LISTING_TYPE};

    public static final String[] RECURSIVE_LIST = new String[] {User.WATCHED_SEARCH_QUERIES, User.REL_WORK_EXP,
            User.UREL_WORK_EXP, User.LEADERSHIP};

    public void convertValueTypes(Map<String, Object> dataMap){
        for (String key:
             dataMap.keySet()) {
            Object data = dataMap.get(key);
            if (Arrays.asList(DATE_TIME_KEYS).contains(key)){
                data = ICreateEntry.parseDateTime(data);
            }
            else if (Arrays.asList(STRING_ARRAYS).contains(key)){
                data = stringArrayToList((String[]) data);
            }
            else if (Arrays.asList(ENUMS).contains(key)){
                data = enumParse(key, data);
            }
            else if (Arrays.asList(RECURSIVE_LIST).contains(key)){

                List<Map<String, Object>> container = (List<Map<String, Object>>) data;
                for (Map<String, Object> item:
                     container) {
                    convertValueTypes(item);
                }
            }
            dataMap.replace(key, data);
        }
    }

    private ArrayList<String> stringArrayToList(String[] array){
        return new ArrayList<>(Arrays.asList(array));
    }

    private Object enumParse(String key, Object value){
        switch (key){
            case JobListing.JOB_TYPE:
                return JobType.valueOf((String) value);
            case JobListing.LISTING_TYPE:
                return ListingType.valueOf((String) value);
            default:
                return null;
        }
    }

}
