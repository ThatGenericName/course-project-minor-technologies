package Framework.FileIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileIO {

    /**
     * a constant for the root directory.
     */
    public static final String WORK_PATH = System.getProperty("user.dir") + File.separator;

    /**
     * Reads the file at the relative path (relative to the root directory for the project)
     * returns an empty string if the file was unable to be read.
     * @param relPath the path relative to the root directory, must include the / at the start.
     * @return
     */
    public static String ReadFile(String relPath){
        try{
            String targetPath = WORK_PATH + relPath;
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
            String targetPath = WORK_PATH + relPath;
            if (!relPath.endsWith(File.separator)){
                targetPath += File.separator;
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

    /**
     * Returns an ArrayList of all filenames (not including folders) from the specified directory.
     *
     *
     * @param relDir the directory relative to the root directory.
     * @return ArrayList<String> - a string ArrayList containing the names of all files in the directory.
     */
    public static ArrayList<String> GetFileNamesInDir(String relDir){
        return GetFileNamesInDir(relDir, "");
    }

    /**
     * Returns an ArrayList of all filenames (not including folders) with the specified extension from the specified
     * directory.
     *
     *
     * @param relDir the directory relative to the root directory.
     * @param extension the extension to filter by, ie ".json"
     * @return ArrayList<String> - a string ArrayList containing the names of all files in the directory.
     */
    public static ArrayList<String> GetFileNamesInDir(String relDir, String extension){
        String path = WORK_PATH + relDir;

        File[] folder = new File(path).listFiles();

        // I need to test this later
        // TODO: find out why I need this assert
        assert folder != null;
        ArrayList<String> fileNames = new ArrayList<>();

        for (File file : folder) {
            if (file.isFile() && file.getName().endsWith(extension)) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
}
