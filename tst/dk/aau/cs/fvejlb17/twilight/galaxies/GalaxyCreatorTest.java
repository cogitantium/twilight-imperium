package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyCreatorTest {

    @Test
    void createPresetGameLegality() {
        Galaxy galaxy01 = new GalaxyCreator().createPresetGame();

        assertTrue(galaxy01.checkGalaxyLegality());
    }

    @Test
    void createPresetGameCongruency() {
        //test congruency on createPresetGame method
        Galaxy galaxy = new GalaxyCreator().createPresetGame();

        SystemTileList systemTileList = galaxy.getAllSystemsInGalaxy();

        //assert that seven planets exist in galaxy
        assertTrue(systemTileList.getNumOfPlanetsInSystemTileList() == 7);

        //assert that six ships exist in galaxy
        assertTrue(galaxy.getAllShipsInGalaxy().size() == 6);

        //assert that center systemtile contains planets
        assertTrue(systemTileList.get(0).containsPlanets());

        //assert that first planet in center systemTile is Mecatol Rex,
        // note; resource production might differ depending on game settings, therefore, only testing on name
        assertTrue(systemTileList.get(0).getAllPlanetsInSystemTile().get(0).getPlanetName().equals("Mecatol Rex"));

        //for all planets in northern systemTile, assert they must be named either Vega Minor or Vega Major
        for (Planet planet : systemTileList.get(1).getAllPlanetsInSystemTile()) {
            assertTrue(planet.getPlanetName().equals("Vega Minor") || planet.getPlanetName().equals("Vega Major"));
        }

        //assert that north eastern and south western systemTile does not contain planets
        assertTrue(systemTileList.get(2).getAllPlanetsInSystemTile().isEmpty());
        assertTrue(systemTileList.get(5).getAllPlanetsInSystemTile().isEmpty());

        //assert that first planet in south eastern systemTile is Industrex
        assertTrue(systemTileList.get(3).getAllPlanetsInSystemTile().get(0).getPlanetName().equals("Industrex"));

        //for all planets in southern systemTile, assert they must be named either Rigel I or Rigel II
        for (Planet planet : systemTileList.get(4).getAllPlanetsInSystemTile()) {
            assertTrue(planet.getPlanetName().equals("Rigel I") || planet.getPlanetName().equals("Rigel II"));
        }

        //assert that first planet in north western systemTile is Mirage
        assertTrue(systemTileList.get(6).getAllPlanetsInSystemTile().get(0).getPlanetName().equals("Mirage"));

        //assert that center systemTile contains Blue player's two Dreadnoughts and a Destroyer
        int numDreadnoughts = 0;
        int numDestroyer = 0;
        //for all ships found in center systemTile, do
        for (Ships ship : systemTileList.get(0).getAllShipsInSystemTile()) {

            //if ship found is of type Destroyer, do
            if (Destroyer.class.isAssignableFrom(ship.getClass())) {
                //assertTrue that this Destroyer has an owner equal to player Blue
                assertTrue(ship.getOwner().equals(galaxy.getPlayersInGalaxy().get(0)));
                numDestroyer++;


            //if ship found is of type Dreadnought, do
            } else if (Dreadnought.class.isAssignableFrom(ship.getClass())) {
                //assertTrue that this Dreadnought has an owner equal to player Blue
                assertTrue(ship.getOwner().equals(galaxy.getPlayersInGalaxy().get(0)));
                numDreadnoughts++;
            }
        }
        //assertTrue that the expected number of ships in center systemTile has been found
        assertTrue(numDestroyer == 1 && numDreadnoughts == 2);

        //assert that northern systemTile contains Red player's two Cruisers and a Carrier
        int numCruisers = 0;
        int numCarriers = 0;
        //for all ships found in northern systemTile, do
        for (Ships ship : systemTileList.get(1).getAllShipsInSystemTile()) {

            //if ship found is of type Cruiser, do
            if (Cruiser.class.isAssignableFrom(ship.getClass())) {
                //assertTrue that this Destroyer has an owner equal to player Red
                assertTrue(ship.getOwner().equals(galaxy.getPlayersInGalaxy().get(1)));
                numCruisers++;

                //if ship found is of type Carrier, do
            } else if (Carrier.class.isAssignableFrom(ship.getClass())) {
                //assertTrue that this Dreadnought has an owner equal to player Red
                assertTrue(ship.getOwner().equals(galaxy.getPlayersInGalaxy().get(1)));
                numCarriers++;
            }
        }
        //assertTrue that the expected number of ships in northern systemTile has been found
        assertTrue(numCruisers == 2 && numCarriers == 1);
    }
}