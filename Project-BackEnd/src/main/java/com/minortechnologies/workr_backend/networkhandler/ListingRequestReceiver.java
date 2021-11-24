package com.minortechnologies.workr_backend.networkhandler;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ListingRequestReceiver {

    @GetMapping("/Listing/Get/{uuid}")
    public HashMap<String, Object> getListing(@PathVariable String uuid){
        return ListingRequestHandler.getListing(uuid);
    }

    @GetMapping("/Listing/GetMultiple")
    public ArrayList<HashMap<String, Object>> getMultipleListing(@RequestParam String[] uuids){
        return ListingRequestHandler.getListing(uuids);
    }

    @GetMapping("/Listing/Search")
    public ArrayList<HashMap<String, Object>> searchListings(@RequestParam HashMap<String, Object> searchQuery){
        return ListingRequestHandler.searchListings(searchQuery);
    }

    @GetMapping("/Listing/Score/{login}")
    public ArrayList<String[]> score(@RequestParam String token, @PathVariable String login, @RequestParam ArrayList<String> uuids){
        throw new UnsupportedOperationException();
    }
}
