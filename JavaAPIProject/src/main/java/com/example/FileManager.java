package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void saveData(String data) {
        try (FileWriter writer = new FileWriter("JavaAPIProject//src//main//java//com//example//data//ApiCall.txt")) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadData(String file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String output = "";
            String line;
            
            while ((line = reader.readLine()) != null) {
                output += line;
            }
            return output;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

