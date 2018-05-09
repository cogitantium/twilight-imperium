package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.galaxies.GalaxyCreator;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SystemTileListTest {

    @Test
    void moveShipMovementSpeedOne() {
        //create sensible preset game and assert legality
        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();
        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        assertTrue(presetGalaxy.checkGalaxyLegality());

        UnitList unitList = presetGalaxy.getAllShipsInGalaxyByOwnerSorted(playerCrassus);
        SystemTileList systemTileList = presetGalaxy.getAllSystemsInGalaxy();

        //test moveShip method with movementSpeed of one by Crassus' dreadnought from center to north
        assertTrue(systemTileList.moveShip(unitList.get(0), systemTileList.get(0), systemTileList.get(1)));
        //test moveShip with same ship from north to south west
        assertFalse(systemTileList.moveShip(unitList.get(0), systemTileList.get(1), systemTileList.get(5)));
    }

    @Test
    void moveShipMovementSpeedTwo() {
        //create sensible preset game and assert legality
        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();
        Player playerPompey = new Player("Pompey", "Federation of Sol", "Red");
        assertTrue(presetGalaxy.checkGalaxyLegality());

        UnitList unitList = presetGalaxy.getAllShipsInGalaxyByOwnerSorted(playerPompey);
        SystemTileList systemTileList = presetGalaxy.getAllSystemsInGalaxy();

        //test moveShip method with movementSpeed greater than one by Pompey's cruiser
        assertTrue(systemTileList.moveShip(unitList.get(0), systemTileList.get(1), systemTileList.get(6)));

    }

    @Test
    void moveShipToSameSystemTile() {
        //create sensible preset game and assert legality
        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();
        Player playerPompey = new Player("Pompey", "Federation of Sol", "Red");
        assertTrue(presetGalaxy.checkGalaxyLegality());

        UnitList unitList = presetGalaxy.getAllShipsInGalaxyByOwnerSorted(playerPompey);
        SystemTileList systemTileList = presetGalaxy.getAllSystemsInGalaxy();

        //test moveShip method with unreasonable move
        assertFalse(systemTileList.moveShip(unitList.get(0), systemTileList.get(1), systemTileList.get(1)));

    }
}