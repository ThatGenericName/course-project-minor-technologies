import java.io.File;

import org.junit.*;
import static org.junit.Assert.*;

public class FileIOTest {
    String testPath = File.separator + "Project" + File.separator + "test" + File.separator + "FileIOTest.txt";

    @Test
    public void testReadFile() {
        String testText = FileIO.ReadFile(testPath);
        assertEquals(testText, "Hello Java Learner !!");
    }

    @Test
    public void testWriteFile() {
        FileIO.WriteFile(testPath, "FileIOTest2.txt", "Hello Java Learner !");
        String testText = FileIO.ReadFile(testPath);

        assertEquals(testText, "Hello Java Learner !");
    }
}