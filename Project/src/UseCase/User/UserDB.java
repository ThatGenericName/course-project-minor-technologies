package UseCase.User;

import Entities.IEntry;
import Entities.User.User;
import UseCase.IDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class UserDB implements IDatabase {

    private final ArrayList<User> userList;

    public UserDB(){
       this.userList = new ArrayList<>();
    }

    public UserDB(Iterable<User> users){
        this.userList = new ArrayList<>();
        for (User user:
             users) {
            userList.add(user);
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    /** TODO: finish docstring
     * Returns a user based on the login and password.
     *
     * returns null if either login or password does not match any users in the Database
     *
     * @param login
     * @param password
     * @return
     */
    public User signIn(String login, String password){
        for (User user:
             userList) {
            if (user.matchLogin(login) && user.matchLogin(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean addEntry(IEntry entry) {
//        if (entry instanceof User && userList.contains((User) entry)){
//            userList.add((User) entry);
//        }
        //TODO: Test if this inline thingy works.
        return (entry instanceof User) && (userList.contains((User) entry)) && userList.add((User) entry);
    }

    @Override
    public boolean removeEntry(IEntry entry) {
        return (entry instanceof User) && userList.remove((User) entry);
    }

    //TODO: Complete this method.
    @Override
    public boolean updateEntry(IEntry entry) {
        throw new UnsupportedOperationException("UserDB.UpdateEntry() has not been implemented");
    }

    @Override
    public boolean contains(IEntry entry) {
        return ((entry instanceof User) && userList.contains((User) entry));
    }

    @Override
    public int size() {
        return userList.size();
    }

    @Override
    public Iterator<IEntry> iterator() {
        return new UserDBIterator(this.userList);
    }

    static class UserDBIterator implements  Iterator<IEntry>{

        private final Iterator<User> toIterate;

        public UserDBIterator(ArrayList<User> userList){
            toIterate = userList.iterator();
        }

        @Override
        public boolean hasNext() {
            return toIterate.hasNext();
        }

        @Override
        public IEntry next() {
            return toIterate.next();
        }
    }
}
