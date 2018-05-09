package dk.aau.cs.fvejlb17.twilight.utilities;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.galaxies.GalaxyCreator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;


class WriteGameStateToFileTest {

    @Test
    void createPlanetaryControlFile() {
        //create constants for use in writing file
        final String OUTPUTDIR = "gameData";
        final String PLANETARYCONTROLFILE = "planetaryControlFile.txt";

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

    @Test
    void createPlanetaryControlFileTestNoPlayers() {
        //create constants for use in writing file
        final String OUTPUTDIR = "gameData";
        final String PLANETARYCONTROLFILE = "planetaryControlFile.txt";

        //create prerequisite, presetGalaxy, path to be tested and path to expected file
        Galaxy galaxy = new GalaxyCreator().createPresetGame();
        Path planetaryControlFile = Paths.get(OUTPUTDIR + "/" + PLANETARYCONTROLFILE);
        Path expectedPlanetaryControlFile = Paths.get(
                "tst/dk/aau/cs/fvejlb17/twilight/utilities/expectedPlanetaryControlFileNoPlayers.txt");

        //remove all players from galaxy and assert that they've been removed
        galaxy.getPlayersInGalaxy().clear();
        assertTrue(galaxy.getPlayersInGalaxy().isEmpty());

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