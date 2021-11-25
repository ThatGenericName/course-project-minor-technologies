package com.minortechnologies.workr_backend.networkhandler;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ListingRequestReceiver {

    @GetMapping("/JobListing/Get/{uuid}")
    public Map<String, Object> getListing(@PathVariable String uuid){
        return ListingRequestHandler.getListing(uuid);
    }

    @GetMapping("/JobListing/GetMultiple")
    public ArrayList<HashMap<String, Object>> getMultipleListing(@RequestBody String[] uuids){
        return ListingRequestHandler.getListing(uuids);
    }

    @GetMapping("/JobListing/Search")
    public ArrayList<HashMap<String, Object>> searchListings(@RequestParam String dateTime, String location, String jobType, String searchTerms){

        HashMap<String, Object> query = new HashMap<>();
        query.put("dateTime", dateTime);
        query.put("location", location);
        query.put("jobType", jobType);
        query.put("searchTerms", searchTerms);
        return ListingRequestHandler.searchListings(query);
    }

    @GetMapping("/JobListing/Score/{login}")
    public ArrayList<String[]> score(@RequestParam String token, @PathVariable String login, @RequestBody String[] payload){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/JobListing/CreateCustom/{login}}")
    public String createCustomListing(@RequestParam String token, @PathVariable String login, @RequestBody Map<String, Object> payload){
        return null;
    }
}
