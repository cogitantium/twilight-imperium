package dk.aau.cs.fvejlb17.twilight.galaxies.randomnames;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NameFileReader {

    public static List<String> getLinesFromFile(String type) {

        //declaring constants of filenames of sample strings and source directory
        String file = "";
        final String directory = "gameData/randomNames/";
        final Charset charset = Charset.forName("UTF-8");

        //switch for type of sample strings wanted by method parameter
        switch (type) {
            case "planet":
                file = "planetNames.txt";
                break;
            case "player":
                file = "playerNames.txt";
                break;
            case "races":
                file = "playerRaces.txt";
                break;
            case "colour":
                file = "playerColours.txt";
                break;
        }
        try {
            //create Path of sourcefile and return all lines as String collection
            Path sourceFile = Paths.get(directory + file);
            return Files.readAllLines(sourceFile, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if fileLines hasn't been found and exception hasn't been caught, return empty list
        return new ArrayList<>();
    }
}

