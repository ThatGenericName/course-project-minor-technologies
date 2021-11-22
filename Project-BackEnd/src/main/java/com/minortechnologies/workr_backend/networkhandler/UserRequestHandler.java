package com.minortechnologies.workr_backend.networkhandler;

import com.minortechnologies.workr_backend.controllers.usermanagement.AuthTokenController;
import com.minortechnologies.workr_backend.controllers.usermanagement.UserManagement;
import com.minortechnologies.workr_backend.entities.user.User;
import com.minortechnologies.workr_backend.usecase.factories.EntryDataMapTypeCaster;
import com.minortechnologies.workr_backend.usecase.fileio.JSONSerializer;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserRequestHandler {

    /**
     * authenticates a login and password. If there is a User Account with the matching
     * password and login, returns a token. Otherwise returns null.
     *
     * @param login
     * @param password
     * @return
     */
    public static String authenticateSignIn(String login, String password){
        return Application.getUserManagement().signIn(login, password);
    }


    public static HashMap<String, Object> getAccountData(String login, String token){
        if (authenticateToken(login, token)){
            User user = Application.getUserManagement().getUserByLogin(login);
            HashMap<String, Object> userData = user.serialize();
            removePrivateData(userData);
            return userData;
        }
        return null;
    }

    /**
     * removes sensitive data (such as passwords, salt, etc) from a hashmap and
     * replaces the data with null keys.
     *
     * @param data the dataset to be altered.
     */
    private static void removePrivateData(HashMap<String, Object> data){
        data.put(User.SALT, null);
        data.put(User.HASHED_PASSWORD, null);
    }

    /**
     * Authenticates that a token belongs to the specified login, and that the
     * token is not expired.
     *
     * @param token the token
     * @param login the login to match to the token
     * @return whether the login matches the token
     */
    public static boolean authenticateToken(String login, String token){
        AuthTokenController controller = Application.getAuthTokenController();
        return controller.Authenticate(login, token);
    }


    public static int createUser(String username, String email, String login,
                                 String password){
        UserManagement um = Application.getUserManagement();
        if (!um.createUser(username, login, email, password)){
            return 4;
        }
    }

    public static int setUserData(String login, String token, String key, String data){
        if (!authenticateToken(login, token)){
            return 0;
        }
        if (!Arrays.asList(User.KEYS).contains(key)){
            return 2;
        }

        UserManagement um = Application.getUserManagement();
        User targetUser = um.getUserByLogin(login);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put(key, data);

        EntryDataMapTypeCaster edmtc = new EntryDataMapTypeCaster();
        try{
            edmtc.convertValueTypes(dataMap);
        }
        catch (ClassCastException e){
            return 3;
        }

        targetUser.updateEntry(dataMap);
    }

    public static int setUserdata(String login, String token, String data){
        if (!authenticateToken(login, token)){
            return 0;
        }
        Map<String, Object> dataMap = new JSONSerializer().deserialize(data);
        if (dataMap == null){
            return 3;
        }
        HashMap<String, Object> dataCopy = SerializationUtils.clone((HashMap<String, Object>)dataMap);

        (new EntryDataMapTypeCaster()).convertValueTypes(dataCopy);

        int count = EntryDataMapTypeCaster.malformedDataCount(dataCopy, dataMap);

        boolean unrecogKeys = false;
        for (String key:
                dataMap.keySet()) {
            if (!Arrays.asList(User.KEYS).contains(key)){
                unrecogKeys = true;
                break;
            }
        }

        UserManagement um = Application.getUserManagement();
        User targetUser = um.getUserByLogin(login);

        targetUser.updateEntry(dataMap);

        if (unrecogKeys){
            if (count > 0){
                return 6;
            }
            return 4;
        }
        if (count > 0){
            return 5;
        }
        return 1;
    }


}
