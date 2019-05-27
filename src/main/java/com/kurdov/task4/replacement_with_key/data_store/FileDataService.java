package com.kurdov.task4.replacement_with_key.data_store;

import java.io.*;

public class FileDataService {
    /**
     * Reading from text file
     */
    public String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder data = new StringBuilder();

        while (reader.ready()) {
            data.append(reader.readLine());
        }

        reader.close();

        return data.toString();
    }

    /**
     * Writing into text file
     * FileWriter class can be used by itself
     */
    public void writeStringsToFile(String filename, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        writer.write(data);
        writer.newLine();
        writer.flush();

        writer.close();
    }
}
