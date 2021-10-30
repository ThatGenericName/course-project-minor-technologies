package Controllers.UserManagement;

import Entities.IEntry;
import Entities.User.User;
import UseCase.IDatabase;
import UseCase.User.CreateUser;
import UseCase.User.UserDB;

import java.util.Scanner;

public class UserManagement {

    public UserDB getUserDatabase() {
        return userDatabase;
    }

    private final UserDB userDatabase;

    private User currentActiveUser;

    public UserManagement(Iterable<User> users){
        this.userDatabase = new UserDB(users);
        this.currentActiveUser = null;
    }

    public UserManagement(){
        this.userDatabase = new UserDB();
        this.currentActiveUser = null;
    }

    public User getCurrentActiveUser() {
        return currentActiveUser;
    }

    public boolean signIn(String login, String password){
        User user = userDatabase.signIn(login, password);

        return (user != null) && setActiveUser(user);
    }

    private boolean setActiveUser(User user){
        currentActiveUser = user;
        return true;
    }

    public boolean createUser(String username, String login, String password){
        User newUser = new CreateUser().create(username, login, password);

        return userDatabase.addEntry(newUser);
    }

    public boolean containsLogin(String login){
        return userDatabase.contains(login);
    }
}
