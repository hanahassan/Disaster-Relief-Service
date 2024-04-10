package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String genderFile = "ucalgary/oop/GenderOptions.txt";

    public FileManager() {
    }

    public void setGenderFile(String name) {
        genderFile = name;
    }

    public List<String> readConfigurationFile() {
        List<String> genderOptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(genderFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read gender options file.");
            System.out.println("Please ensure the file '" + genderFile + "' exists and can be accessed.\n");
        }
        return genderOptions;
    }
}
