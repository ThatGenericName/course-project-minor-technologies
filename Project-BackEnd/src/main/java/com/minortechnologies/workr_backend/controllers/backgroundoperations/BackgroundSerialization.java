package com.minortechnologies.workr_backend.controllers.backgroundoperations;

import com.minortechnologies.workr_backend.networkhandler.Application;

public class BackgroundSerialization implements IBackgroundOperation{
    @Override
    public void run() {
        while (BackgroundOperations.isRunBackgroundOps()){
            try {
                serialize();
                Thread.sleep(BackgroundOperations.getUpdateInterval());
            } catch (InterruptedException e) {
                serialize();
            }
        }
    }

    private void serialize(){
        Application.getUserManagement().saveUsers();
        Application.getLocalCache().saveAllListings();
        Application.getAuthTokenController().saveTokens();
    }
}
