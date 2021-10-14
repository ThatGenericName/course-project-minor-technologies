import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;

public class Main {

    //TODO: Implement a basic, text based Java Console UI. This UI should allow the user to search for a listing, and
    //TODO: then view the listing. Check SearchQuery to see what parameters need to exist.
    //TODO: You might want to make a separate UI class in case Main needs to do other things.
    //TODO: First thing main() should do is load files. Call LocalCache, it has a methods to load saved files.
    public static void main(String[] args) {
        System.out.println("Go Minor Technologies!");

        String wrkPath = System.getProperty("user.dir");
        System.out.println(wrkPath);

        try{
            String file = wrkPath + "\\test.json";
            Path path = Path.of(file);
            String jsonData = Files.readString(path);

            // this is the JSON file. Read the org.json documentation here
            // https://www.javadoc.io/doc/org.json/json/latest/index.html
            // For the most part, it's effectively the same as a Python Dict,
            // just that it's keys are always Strings, and it's values are stored
            // as Objects, and so they may need to be cast.

            JSONObject test = new JSONObject(jsonData);

            // a little for loop of the json file.

            Iterator<String> jsonKeys = test.keys();
            System.out.println(test.get("object") instanceof HashMap);

            while(jsonKeys.hasNext()){
                String currentJsonKey = jsonKeys.next();
                if (test.get(currentJsonKey) instanceof JSONObject){
                    JSONObject nestedJson = (JSONObject) test.get(currentJsonKey);

                    Iterator<String> nestedJsonKeys = nestedJson.keys();

                    while (nestedJsonKeys.hasNext()){
                        String currentNestedJsonKey = nestedJsonKeys.next();
                        System.out.print("    ");
                        System.out.println(currentNestedJsonKey + " - " + nestedJson.get(currentNestedJsonKey));

                    }

                }
                System.out.println(currentJsonKey + " - " + test.get(currentJsonKey));
            }

            // This while loop can be made a recursive method to continuously print nested JSON maps
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}