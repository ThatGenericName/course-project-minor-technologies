import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIO {

    private static final String wrkPath = System.getProperty("user.dir");

    /**
     * Reads the file at the relative path (relative to the root directory for the project)
     * returns an empty string if the file was unable to be read.
     * @param relPath the path relative to the root directory, must include the / at the start.
     * @return
     */
    public static String ReadFile(String relPath){
        try{
            String targetPath = wrkPath + relPath;
            Path path = Path.of(targetPath);
            return Files.readString(path);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Writes data to relPath with the name fileName
     *
     * Returns true if file was successfully written, false otherwise.
     * @param relPath - The path relative to the root directory of the project to write the file to.
     * @param fileName - the name for the file.
     * @param data - The data to be written, in any format
     * @return
     */
    public static boolean WriteFile(String relPath, String fileName, String data){
        try{
            String targetPath = wrkPath + relPath;
            if (!relPath.endsWith("/")){
                targetPath += "/";
            }
            targetPath += fileName;

            Path path = Path.of(targetPath);

            if (!Files.exists(path)){
                Files.createFile(path);
            }

            Files.writeString(path, data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
