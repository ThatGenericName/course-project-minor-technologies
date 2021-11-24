package com.minortechnologies.workr_backend.networkhandler;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class UserRequestReceiver {


    @PostMapping("/User/SignIn")
    public String signIn(@RequestParam String login, @RequestParam String password){
        return UserRequestHandler.authenticateSignIn(login, password);
    }

    @PostMapping("/User/SignInByToken")
    public boolean signInByToken(@RequestParam String login, @RequestParam String token){
        return UserRequestHandler.authenticateToken(login, token);
    }

    @PostMapping("/User/Create")
    public int createUser(@RequestParam String username, @RequestParam String email, @RequestParam String login,
                             @RequestParam String password){
        return UserRequestHandler.createUser(username, email, login, password);
    }

    @PostMapping("User/{login}/SetData")
    public int setUserData(@PathVariable String login, @RequestParam String token, @RequestParam String key, @RequestParam String data){
        return UserRequestHandler.setUserData(login, token, key, data);
    }

    @PostMapping("User/{login}/SetDataLarge")
    public int setUserData(@PathVariable String login, @RequestParam String token, @RequestParam String data){
        return UserRequestHandler.setUserdata(login, token, data);
    }

    @PostMapping("/User/{login}/GetAllUserData")
    public HashMap<String, Object> getAllUserData(@PathVariable String login, @RequestParam String token){
        return UserRequestHandler.getAccountData(login, token);
    }

    @PostMapping("/User/{login}/GetWatchedListings")
    public HashMap<String, Object> getWatchedListings(@PathVariable String login, @RequestParam String token){
        return UserRequestHandler.getUserWatchedListings(login, token);
    }

    @PostMapping("/User/{login}/GetCustomListings")
    public HashMap<String, Object> getCustomListings(@PathVariable String login, @RequestParam String token){
        return UserRequestHandler.getUserCustomListings(login, token);
    }

    @PostMapping("/User/{login}/AddToWatched")
    public String addToWatchedListing(@PathVariable String login, @RequestParam String token, @RequestParam HashMap<String, Object> listing){
        return UserRequestHandler.addToWatchedListing(login, token, listing);
    }
}
