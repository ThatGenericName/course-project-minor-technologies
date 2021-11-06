package UseCase.User;

import Entities.Entry;
import Entities.User.User;
import UseCase.IDatabase;
import UseCase.Security.Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * A Database of Users
 *
 * For all intents and purposes, this is basically a HashSet, however I need some specific methods for this,
 * and HashSets in my own testing resulted in some odd and rather inconsistent behavior.
 */
public class UserDB implements IDatabase {

    private final HashMap<userDBHashWrapper, User> userHashMap;

    public UserDB(){
       this.userHashMap = new HashMap<>();
    }

    public UserDB(Iterable<User> users){
        this.userHashMap = new HashMap<>();
        for (User user:
             users) {
            userHashMap.put(new userDBHashWrapper(user), user);
        }
    }

    public ArrayList<User> getUserHashMap() {

        return new ArrayList<>(userHashMap.values());
    }

    /** TODO: finish docstring
     * Returns a user based on the login and password.
     *
     * returns null if either login or password does not match any users in the Database
     *
     * @param login - A String, representing a login key.
     * @param password - A String, representing a password.
     * @return
     */
    public User signIn(String login, String password){
        User loginMatchedUser = userHashMap.get(new userDBHashWrapper(login));
        if (loginMatchedUser != null && Security.authenticate(password, loginMatchedUser)){
            return loginMatchedUser;
        }
        return null;
    }

    @Override
    public boolean addEntry(Entry entry) {

        if (entry instanceof User){
            User user = (User) entry;
            userDBHashWrapper hashWrap = new userDBHashWrapper(user);
            if (!userHashMap.containsKey(hashWrap)){
                userHashMap.put(new userDBHashWrapper(user), user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeEntry(Entry entry) {
        if (entry instanceof User){
            User user = (User) entry;

            User ret = userHashMap.remove(new userDBHashWrapper(user));

            return (ret != null);
        }
        return false;
    }

    //TODO: Complete this method.
    @Override
    public boolean updateEntry(Entry entry) {
        throw new UnsupportedOperationException("UserDB.UpdateEntry() has not been implemented");
    }

    /**
     * Whether this database contains this specific entry.
     *
     * @param entry
     * @return
     */
    @Override
    public boolean contains(Entry entry) {
        return ((entry instanceof User) && userHashMap.containsKey(new userDBHashWrapper((User) entry)));
    }

    /**
     * Whether this database contains an entry with a matching login.
     *
     * If there is a key with the matching login, then there is a User with the matching login in the database.
     *
     * @param login - String - the login to check if there is a user in the Database with the matching login.
     * @return boolean - whether there is an entry with the specified login.
     */
    public boolean contains(String login){
        return userHashMap.containsKey(new userDBHashWrapper(login));
    }

    @Override
    public int size() {
        return userHashMap.size();
    }

    @Override
    public Iterator<Entry> iterator() {

        return new UserDBIterator(getUserHashMap());
    }

    static class UserDBIterator implements  Iterator<Entry>{

        private final Iterator<User> toIterate;

        public UserDBIterator(ArrayList<User> userList){
            toIterate = userList.iterator();
        }

        @Override
        public boolean hasNext() {
            return toIterate.hasNext();
        }

        @Override
        public Entry next() {
            return toIterate.next();
        }
    }

    /**
     * A wrapper for a user for hashing purposes for the Database
     */
    static class userDBHashWrapper {

        public String getUserLogin() {
            return userLogin;
        }

        private final String userLogin;

        public userDBHashWrapper(User user){
            this.userLogin = (String) user.getData(User.LOGIN);
        }

        public userDBHashWrapper(String login){
            this.userLogin = login;
        }

        @Override
        public int hashCode(){
            return userLogin.hashCode();
        }

        @Override
        public boolean equals(Object other){
            if (other instanceof userDBHashWrapper){
                String otherLogin = ((userDBHashWrapper) other).getUserLogin();

                return (userLogin.equals(otherLogin));
            }
            return false;
        }
    }
}
