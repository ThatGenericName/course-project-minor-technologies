package UseCase.User;

import Entities.User.User;
import UseCase.FileIO.MalformedDataException;
import UseCase.Security.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CreateUser implements ICreateUser{

    @Override
    public User create(Map<String, Object> UserDataMap) throws MalformedDataException {
        return null;
    }

    @Override
    public User create(String username, String login, String password) {
        byte[] saltArr = Security.generateSalt();
        String salt = Security.toHex(saltArr);
        String hashedPassword = Security.toHex(Security.generateHash(password, saltArr));

        return new User(username, login, hashedPassword, salt);
    }

    @Override
    public ArrayList<String> verifyMapIntegrity(Map<String, Object> userDataMap) {
        return null;
    }
}
