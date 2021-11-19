package com.minortechnologies.workr.UseCase.Factories.UserFactory;

import com.minortechnologies.workr.Entities.User.User;
import com.minortechnologies.workr.UseCase.Factories.ICreateEntry;
import com.minortechnologies.workr.UseCase.FileIO.MalformedDataException;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.Map;

public interface ICreateUser extends ICreateEntry {
    User create(Map<String, Object> UserDataMap) throws MalformedDataException;

    User create(String username, String login, String password);

    static @NotNull
    ArrayList<String> verifyMapIntegrityStatic(Map<String, Object> userDataMap){
        throw new UnsupportedOperationException();
    }


}
