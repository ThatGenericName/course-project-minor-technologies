import java.io.File;

import Framework.FileIO.FileIO;
import org.junit.*;
import static org.junit.Assert.*;

public class FileIOTest {
    String testPath = File.separator + "test" + File.separator;

    @Test
    public void testReadFile() {
        String path = testPath + "FileIOTest.txt";
        String testText = FileIO.ReadFile(path);
        assertEquals(testText, "Hello Java Learner !!");
    }

    @Test
    public void testWriteFile() {
        FileIO.WriteFile(testPath, "FileIOTest2.txt", "Hello Java Learner !");
        String testText = FileIO.ReadFile(testPath + "FileIOTest2.txt");

        assertEquals(testText, "Hello Java Learner !");
    }
}