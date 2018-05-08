package dk.aau.cs.fvejlb17.twilight.utilities;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.galaxies.GalaxyCreator;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import org.junit.jupiter.api.Test;

import static dk.aau.cs.fvejlb17.twilight.utilities.WriteGameStateToFile.FilesystemConstants.OUTPUTDIR;
import static dk.aau.cs.fvejlb17.twilight.utilities.WriteGameStateToFile.FilesystemConstants.PLANETARYCONTROLFILE;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


class WriteGameStateToFileTest {

    @Test
    void createPlanetaryControlFile() {

        //create prerequisite, presetGalaxy, path to be tested and path to expected file
        Galaxy galaxy = new GalaxyCreator().createPresetGame();
        Path planetaryControlFile = Paths.get(OUTPUTDIR + "/" + PLANETARYCONTROLFILE);
        Path expectedPlanetaryControlFile = Paths.get(
                "tst/dk/aau/cs/fvejlb17/twilight/utilities/expectedPlanetaryControlFile.txt");
        try {
            //read all lines of expected output into bytearray
            byte[] expectedOutput = Files.readAllBytes(expectedPlanetaryControlFile);

            //try running method to be tested
            WriteGameStateToFile.createPlanetaryControlFile(galaxy);

            //read all lines of actual output into bytearray
            byte[] actualOutput = Files.readAllBytes(planetaryControlFile);

            //assert that bytearrays are equal
            assertTrue(Arrays.equals(expectedOutput, actualOutput));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}