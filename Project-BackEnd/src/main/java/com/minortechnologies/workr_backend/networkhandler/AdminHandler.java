package com.minortechnologies.workr_backend.networkhandler;

import com.minortechnologies.workr_backend.controllers.backgroundoperations.BackgroundOperations;
import com.minortechnologies.workr_backend.controllers.usermanagement.AuthTokenController;
import com.minortechnologies.workr_backend.entities.security.AuthToken;
import com.minortechnologies.workr_backend.usecase.security.AuthTokenDB;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Map;


public class AdminHandler {

    private ApplicationContext appContext;

    /**
     * Serializes all data
     *
     * TODO: if we have time, implement some admin only commands so random people don't shut down the program.
     */
    public static int serializeAllData(){
        BackgroundOperations.endBackgroundThreads();
        Application.getAuthTokenController().saveTokens();
        Application.getLocalCache().saveAllListings();
        Application.getUserManagement().saveUsers();
        BackgroundOperations.startBackgroundLoop();
        return 1;
    }

    public static ArrayList<Map<String, String>> GetAllTokens() {
        AuthTokenController atc = Application.getAuthTokenController();
        AuthTokenDB atdb = atc.getTokenDB();
        ArrayList<Map<String, String>> tokens = new ArrayList<>();

        for (AuthToken at:
             atdb) {
            tokens.add(at.serialize());
        }
        return tokens;
    }

    public static void Shutdown() {

        System.out.println("Shutdown Command Received");
        //TODO: get this to actually shutdown. Doesnt seem to work.
        serializeAllData();

        int exitCode = SpringApplication.exit(Application.getCtx(), new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 0;
            }
        });
        System.exit(exitCode);
    }
}
