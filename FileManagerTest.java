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
        assertEquals(7, genderOptions.size());
        assertEquals("boy", genderOptions.get(0));
        assertEquals("gender queer", genderOptions.get(1));
        assertEquals("girl", genderOptions.get(2));
        assertEquals("man", genderOptions.get(3));
        assertEquals("non-binary", genderOptions.get(4));
        assertEquals("two-spirit", genderOptions.get(5));
        assertEquals("woman", genderOptions.get(6));
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
