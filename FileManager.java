package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String genderFile;

    public FileManager(String genderFile) {
        this.genderFile = genderFile;
    }

    public List<String> readConfigurationFile(String filePath) throws IOException {
        List<String> genderOptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim());
            }
        }
        return genderOptions;
    }

    public void openConfigurationFile() throws IOException {
        readConfigurationFile(genderFile);
    }
}
