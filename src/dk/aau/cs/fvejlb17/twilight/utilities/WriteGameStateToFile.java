package dk.aau.cs.fvejlb17.twilight.utilities;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static dk.aau.cs.fvejlb17.twilight.utilities.WriteGameStateToFile.FilesystemConstants.OUTPUTDIR;
import static dk.aau.cs.fvejlb17.twilight.utilities.WriteGameStateToFile.FilesystemConstants.PLANETARYCONTROLFILE;

public class WriteGameStateToFile {

    class FilesystemConstants {
        static final String OUTPUTDIR = "gameData";
        static final String PLANETARYCONTROLFILE = "planetaryControlFile.txt";
    }

    public static void createPlanetaryControlFile(Galaxy galaxy) throws IOException {
        //create output directory if it doesn't exist
        if (!Files.exists(Paths.get(OUTPUTDIR))) Files.createDirectory(Paths.get(OUTPUTDIR));

        //create Path for planetaryControlFile
        Path planetaryControlFile = Paths.get(OUTPUTDIR + "/" + PLANETARYCONTROLFILE);

        if (Files.exists(planetaryControlFile)) Files.delete(planetaryControlFile);

        //create header with typical information on galaxy configuration and write header to new file
        byte[] header = ("Planetary control output of " + galaxy.toString() + "\n").getBytes("UTF-8");
        Files.write(planetaryControlFile, header, StandardOpenOption.CREATE);

        //if galaxy is created with an empty PlayerList, printing further info is nonsensical,
        // returns after printing warning message
        if (galaxy.getPlayersInGalaxy().isEmpty()) {
            byte[] errorMessage =
                    "WARNING: Galaxy does not contain any players, any output is irrelevant!\n".getBytes("UTF-8");
            Files.write(planetaryControlFile, errorMessage, StandardOpenOption.APPEND);
            return;
        }

        //begin printing actual planetary information, for each players in galaxy, do
        for (Player player : galaxy.getPlayersInGalaxy()) {
            //construct playerHeader from current player and append to file
            Files.write(planetaryControlFile,
                    (player.getColour() + " Player: " + player.getName() + " (" + player.getRace() + ")\n").getBytes("UTF-8"),
                    StandardOpenOption.APPEND);

            //for all SystemTiles in galaxy provided, do
            for (SystemTile systemTile : galaxy.getAllSystemsInGalaxy()) {

                //if SystemTile contains planets, and a controlling player exists, and is current player for first planet, do
                if (!systemTile.getAllPlanetsInSystemTile().isEmpty()
                        && systemTile.getAllPlanetsInSystemTile().get(0).getControllingPlayer() != null &&
                        systemTile.getAllPlanetsInSystemTile().get(0).getControllingPlayer().equals(player)) {

                    //write header with SystemPosition of systemTile to file
                    Files.write(planetaryControlFile,
                            ("SystemTile at: " + systemTile.getSystemPosition().toString() + "\n").getBytes(),
                            StandardOpenOption.APPEND);

                    //for all planets in systemTile which satisfy relevant criteria above, do
                    for (Planet planet : systemTile.getAllPlanetsInSystemTile()) {
                        Files.write(planetaryControlFile, ("\t" + planet.getPlanetName() + "\n").getBytes(),
                                StandardOpenOption.APPEND);
                    }
                }
            }
            //write newline-char to file when all systems has been handled for better formatting
            Files.write(planetaryControlFile, "\n".getBytes(), StandardOpenOption.APPEND);
        }
    }
}
