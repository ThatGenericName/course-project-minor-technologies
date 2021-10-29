package Controllers.UserManagement;

import Entities.IEntry;
import Entities.User.User;
import UseCase.IDatabase;
import UseCase.User.UserDB;

import java.util.Scanner;

public class UserManagement {

    private UserDB userDatabase;

    private User currentActiveUser;

    public UserManagement(Iterable<User> users){
        this.userDatabase = new UserDB(users);
        this.currentActiveUser = null;
    }

    public boolean signIn(String login, String password){
        User user = userDatabase.signIn(login, password);

        return (user != null) && setActiveUser(user);
    }

    private boolean setActiveUser(User user){
        currentActiveUser = user;
        return true;
    }

    public boolean createUser(String login, String password){
        throw new UnsupportedOperationException();
    }

    public void signInDemo(){

        boolean userSet = false;
        Scanner c = new Scanner(System.in);
        while(!userSet){

            System.out.print("Enter your login: ");
            String login = c.nextLine();

            System.out.print("Enter you Password: ");
            String psw = c.nextLine();

            if (signIn(login, psw)){
                System.out.println("You have successfully signed in!");
                System.out.println("Welcome, " + currentActiveUser.getName());
                userSet = true;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Demo Ending");
            }
            else{
                System.out.println("Login or Password does not match!");
                System.out.println("Please re-enter your login and password");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
