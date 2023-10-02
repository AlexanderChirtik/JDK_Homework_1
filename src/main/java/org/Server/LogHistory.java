package org.Server;

import com.sun.source.tree.BreakTree;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogHistory {

    LogHistory() {
        try {
            File log = new File("logHistory.txt");
        }
        catch (Exception e) {}
    }

    public void writeFile(String text) {
        try(FileWriter writer = new FileWriter("logHistory.txt", true)) {
            writer.write(text);
        }
        catch (IOException e){};
    }

    public String readFile() {
        try(FileReader reader = new FileReader("logHistory.txt")) {
            StringBuilder tempText = new StringBuilder();
            int temp;
            while ((temp = reader.read()) != -1) {
                tempText.append((char)temp);
            }
            return tempText.toString();
        }
        catch (IOException e) {};
        return "";
    }
}
