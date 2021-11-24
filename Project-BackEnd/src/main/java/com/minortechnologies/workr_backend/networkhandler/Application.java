package com.minortechnologies.workr_backend.networkhandler;

import java.util.Arrays;


import com.minortechnologies.workr_backend.controllers.localcache.LocalCache;
import com.minortechnologies.workr_backend.controllers.usermanagement.AuthTokenController;
import com.minortechnologies.workr_backend.controllers.usermanagement.UserManagement;
import com.minortechnologies.workr_backend.demo.demosource.DemoJobListingSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static LocalCache localCache;
    private static UserManagement userManagement;
    private static AuthTokenController authTokenController;
    private static DemoJobListingSource demoSource;

    public static DemoJobListingSource getDemoSource(){
        return demoSource;
    }

    public static LocalCache getLocalCache() {
        return localCache;
    }

    public static UserManagement getUserManagement() {
        return userManagement;
    }


    public static AuthTokenController getAuthTokenController(){
        return authTokenController;
    }

    public static void main(String[] args) {
        localCache = new LocalCache();
        userManagement = new UserManagement();
        authTokenController = new AuthTokenController(userManagement);
        demoSource = new DemoJobListingSource();

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}