package Controllers.UserManagement;

import Controllers.DataProcessing.DataFormat;
import Entities.Entry;
import Entities.User.User;
import Framework.FileIO.FileIO;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.User.CreateUser;
import UseCase.User.UserDB;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

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
        loadUsers();
    }

    public void loadUsers(){
        ArrayList<Entry> entries = DataFormat.loadEntiresFromDirectorySub(FileIO.USERS);

        userDatabase.addEntries(entries);
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

    public void saveUsers(){

        IEntrySerializer serializer = new JSONSerializer();

        saveUsers(serializer);
    }

    public void saveUsers(IEntrySerializer serializer){
        if (!FileIO.containsFolder(FileIO.USERS)){
            FileIO.createDirectory(FileIO.USERS);
        }
        for (Entry entry:
             userDatabase) {
            String userPath = FileIO.USERS + File.separator + entry.getSerializedFileName();
            if (!FileIO.containsFolder(userPath)){
                FileIO.createDirectory(userPath);
            }
            String data = serializer.serialize(entry.serialize());
            String saveName = "entry_"+ entry.getSerializedFileName() + serializer.serializerExtension();
            FileIO.WriteFile(userPath, saveName, data);
        }
    }

    public boolean containsLogin(String login){
        return userDatabase.contains(login);
    }
}
