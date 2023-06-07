package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int byteValue;
            while ((byteValue = inputStream.read()) != -1) {
                output.append((char) byteValue);
            }

            String stringInFile = output.toString();
            return parseProfile(stringInFile);

        } catch (IOException ex) {
            return null;
        }
    }

    private Profile parseProfile(String stringInFile) {
        Profile profile = new Profile();
        String[] lines = stringInFile.split("\n");
        for (String line : lines) {
            String[] keyValue = line.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                if (key.equals("Name")) {
                    profile.setName(value);
                } else if (key.equals("Age")) {
                    profile.setAge(Integer.parseInt(value));
                } else if (key.equals("Email")) {
                    profile.setEmail(value);
                } else if (key.equals("Phone")) {
                    profile.setPhone(Long.parseLong(value));
                }
            }
        }
        return profile;
    }
}
