package UseCase.User;

import Entities.Listing.JobListing;
import Entities.Listing.ListingType;
import Entities.User.User;
import UseCase.FileIO.MalformedDataException;
import UseCase.ICreateEntry;
import UseCase.Listing.CreateCustomListing;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface ICreateUser extends ICreateEntry {
    User create(Map<String, Object> UserDataMap) throws MalformedDataException;

    static @NotNull ArrayList<String> verifyMapIntegrityStatic(Map<String, Object> userDataMap){
        throw new UnsupportedOperationException();
    }


}
