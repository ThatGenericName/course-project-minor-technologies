import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.*;
import src.FileIO;

public class FileIOTest {
    FileIO fi;
    Path filePath = Paths.get("C:/", "temp", "test.txt");
    String relPath = "test.txt";

    @Test
    public testReadFile() {
        assertEquals(fi.ReadFile(relPath), "Hello Java Learner !!");
    }

    @Test
    public testWriteFile() {
        assertEquals(fi.WriteFile(relPath, "test.txt", "Hello Java Learner !!"), true);
    }
}