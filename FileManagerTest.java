/** 
@author: Hana Hassan
@ucid: 30172447
@version: 1.3
@since: 1.0

The FileManagerTest class contains test methods to verify the functionality of the FileManager class, 
which manages file operations related to configuration files. 
The testReadConfigurationFile method ensures that the readConfigurationFile method correctly retrieves gender options from the configuration file, 
while the testOpenConfigurationFile method tests the behavior of opening a nonexistent configuration file and expects an IOException to be thrown. 
*/

package edu.ucalgary.oop;

import org.junit.Test;

import com.apple.eio.FileManager;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class FileManagerTest {

    // Test for reading configuration file
    // Objective: Ensure the "readConfigurationFile()" method correctly retrieves
    // gender options from the configuration file.
    // Actual result: The method reads the configuration file and returns a list of
    // gender options.
    // Expected result: At least one gender option should be retrieved from the
    // file.

    @Test
    public void testReadConfigurationFile() throws IOException {
        FileManager fileManager = new FileManager();
        List<String> genderOptions = fileManager.readConfigurationFile();

        // Check if the number of genders read is greater than 0
        assertTrue("At least one gender should be read from the file", genderOptions.size() > 0);
    }
    
    // Test for opening nonexistent configuration file
    // Objective: Ensure the "openConfigurationFile()" method throws an IOException
    // when attempting to open a nonexistent file.
    // Actual result: The method correctly throws an IOException when attempting to
    // open a nonexistent file.
    // Expected result: An IOException should be thrown when attempting to open a
    // nonexistent file.
    @Test
    public void testOpenConfigurationFile() {
        FileManager fileManager = new FileManager();
        fileManager.setGenderFile("NonExistentFile.txt");
        try {
            fileManager.openConfigurationFile();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            // Expected IOException
        }
    }
}
