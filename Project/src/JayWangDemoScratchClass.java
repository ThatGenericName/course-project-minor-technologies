/**
 * This is a scratch file that exists purely for debugging and internal demos.
 *
 * The contents of this file does not affect the rest of the program in any way.
 *
 */

import Controllers.DataProcessing.DataFormat;
import Controllers.LocalCache.LocalCache;
import Controllers.UserManagement.UserManagement;
import Entities.IEntry;
import Entities.Listing.JobListing;
import Entities.User.User;
import Framework.FileIO.FileIO;
import Main.Main;
import UseCase.FileIO.IEntrySerializer;
import UseCase.FileIO.JSONSerializer;
import UseCase.FileIO.MalformedDataException;
import UseCase.User.UserDB;

import java.io.File;
import java.util.*;

/**
 * A little demo for writing to and from a json file, and loading it as a Entities.Listings.Listing Object.
 *
 *
 *
 */
public class JayWangDemoScratchClass {

    private static LocalCache localCache;

    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");

        // formatDemoListings();

        localCache = new LocalCache();
        UserManagement um = new UserManagement();
        Main.setLocalCache(localCache);
        Main.setUserManagement(um);


        accountDemo(null);

        localCache.loadSavedListings();


        System.out.println("ended");
    }

    /**
     * reads all files (that's not named "ListingTemplate.json") from \ListingInOut\Load and
     * saves it in \ListingInOut\Save for UID, Hashing, and Filename generation
     *
     */
    public static void formatDemoListings(){

        String base = File.separator + "ListingInOut" + File.separator;

        String load = base + "Load";
        String save = base + "Save";
        ArrayList<String> files = FileIO.GetFileNamesInDir(load, ".json");

        for (String file : files) {
            if (!(file.equals("ListingTemplate.json"))){
                try {
                    JobListing jobListing = DataFormat.createListing(FileIO.ReadFile(load + File.separator + file));

                    IEntrySerializer serializer = new JSONSerializer();

                    //String listingData = DataFormat.createJSON(listing);

                    String listingData = serializer.serialize(jobListing.serialize());

                    FileIO.WriteFile(save, jobListing.getSerializedFileName() + ".json", listingData);
                } catch (MalformedDataException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void accountDemo(Scanner c){
        if (c == null){
            c = new Scanner(System.in);
        }
        boolean running = true;
        while (running){
            System.out.println("What would you like to do?");
            System.out.println("1: Create Account");
            System.out.println("2: Sign in");
            System.out.println("3: Exit");
            System.out.println("4: print user list & details");

            String choice = c.nextLine();

            switch (choice){
                case "1":
                    createAccountDemo(c);
                    break;
                case "2":
                    loginDemo(c);
                    break;
                case "3":
                    running = false;
                    break;
                case "4":
                    printUserDeets();
                    break;
                default:
                    System.out.println("not good choose");
                    break;
            }
        }
    }

    private static void loginDemo(Scanner c){
        while (true){
            System.out.println("Enter your login");
            String login = c.nextLine();
            System.out.println("Enter your password");
            String pass = c.nextLine();

            if (Main.getUserManagement().signIn(login, pass)){
                System.out.println("Successfully signed in,");
                User user = Main.getUserManagement().getCurrentActiveUser();
                System.out.println("Welcome, " + user.getAccountName());

                break;
            }
            else{
                System.out.println("Username or Password does not match any login details");
                System.out.println("type 'exit' to leave");
                String choice = c.nextLine();
                if (Objects.equals(choice, "exit")){
                    break;
                }
            }
        }
    }

    private static void printUserDeets(){
        UserDB udb = Main.getUserManagement().getUserDatabase();

        for (IEntry entry:
             udb) {
            User user = (User) entry;

            System.out.println("============================================");
            System.out.println("Account Name: {" + user.getAccountName() + "}");
            System.out.println("Account Login: {" + user.getLogin() + "}");
            System.out.println("Account Salt: {" + user.getSalt() + "}");
            System.out.println("Account Password: {" + user.getHashedPassword() + "}");
        }
    }

    private static void createAccountDemo(Scanner c){
        while (true){

            System.out.println("What is your name?");
            String name = c.nextLine();

            String login;

            boolean validLogin = false;
            do {
                System.out.println("Enter a login!");
                login = c.nextLine();
                if (login.length() == 0){
                    System.out.println("This is not a valid login!");
                }
                else if (!Main.getUserManagement().containsLogin(login)){
                    validLogin = true;
                }
                else{
                    System.out.println("'" + login + "' is already taken, please use a different login");
                }
            } while (!validLogin);

            System.out.println("Enter a password:");

            String pass = c.nextLine();


            System.out.println("This will be your account details:");
            System.out.println("Name: " + name + ", login: " + login + ", Password: " + pass);
            System.out.println("Would you like to change anything? (y/n)");

            String choice = c.nextLine();

            if (Objects.equals(choice, "n")){
                Main.getUserManagement().createUser(name, login, pass);
                break;
            }
        }
    }
}
