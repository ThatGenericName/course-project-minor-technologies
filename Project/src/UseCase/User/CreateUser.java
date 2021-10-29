package UseCase.User;

import Entities.User.User;
import UseCase.FileIO.MalformedDataException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CreateUser implements ICreateUser{
    @Override
    public User create(Map<String, Object> UserDataMap) throws MalformedDataException {
        return null;
    }

    @Override
    public ArrayList<String> verifyMapIntegrity(Map<String, Object> userDataMap) {
        return null;
    }
}
