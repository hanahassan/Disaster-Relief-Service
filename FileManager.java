package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String genderFile = "GenderOptions.txt";

    public FileManager() {}

    public void setGenderFile (String name) {
        genderFile = name;
    }

    public List<String> readConfigurationFile() throws IOException {
        List<String> genderOptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(genderFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim());
            }
        }
        return genderOptions;
    }

    public void openConfigurationFile() throws IOException {
        readConfigurationFile();
    }
}
