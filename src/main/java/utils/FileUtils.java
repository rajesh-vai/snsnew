package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Rajesh on 20-02-2018.
 */
public class FileUtils {
    public String readFileToString(String fileName){
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
