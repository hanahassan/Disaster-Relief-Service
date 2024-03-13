package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class FileManagerTest {

    @Test
    public void testReadConfigurationFile() throws IOException {
        FileManager fileManager = new FileManager("GenderOptions.txt");
        List<String> genderOptions = fileManager.readConfigurationFile("GenderOptions.txt");
        
        // Check if the number of genders read is greater than 0
        assertTrue("At least one gender should be read from the file", genderOptions.size() > 0);
    }

    @Test
    public void testOpenConfigurationFile() {
        FileManager fileManager = new FileManager("NonExistentFile.txt");
        try {
            fileManager.openConfigurationFile();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            // Expected IOException
        }
    }
}
