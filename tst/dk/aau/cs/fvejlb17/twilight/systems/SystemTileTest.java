package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.units.Cruiser;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemTileTest {

    @Test
    void getSystemPosition() {
    }

    @Test
    void getNeighbourSystemTiles() {
    }

    @Test
    void shipEnterSystemTile() {
        Player player01 = new Player("playerName", "playerRace", "playerColour");
        SystemTile systemTile01 = new SystemTile(SystemPosition.N);
        Cruiser cruiser01 = new Cruiser(player01);

        assertTrue(systemTile01.shipEnterSystemTile(cruiser01));
    }

    @Test
    void shipLeaveSystemTile() {
        Player player01 = new Player("playerName", "playerRace", "playerColour");
        SystemTile systemTile01 = new SystemTile(SystemPosition.N);
        Cruiser cruiser01 = new Cruiser(player01);

        assertTrue(systemTile01.shipEnterSystemTile(cruiser01));

        assertTrue(systemTile01.shipLeaveSystemTile(cruiser01));
    }

    @Test
    void getAllShipsInSystemTile() {
        Player player01 = new Player("playerName", "playerRace", "playerColour");
        SystemTile systemTile01 = new SystemTile(SystemPosition.N);
        Cruiser cruiser01 = new Cruiser(player01);
        Cruiser cruiser02 = new Cruiser(player01);
        Cruiser cruiser03 = new Cruiser(player01);

        UnitList unitList01 = new UnitList();
        unitList01.add(cruiser01);
        unitList01.add(cruiser02);
        unitList01.add(cruiser03);

        systemTile01.shipEnterSystemTile(cruiser01);
        systemTile01.shipEnterSystemTile(cruiser02);
        systemTile01.shipEnterSystemTile(cruiser03);

        assertTrue(systemTile01.getAllShipsInSystemTile().equals(unitList01));

    }

    @Test
    void getAllPlanetsInSystemTile() {
    }

    @Test
    void getNumOfPlanetsInSystemTile() {
    }

    @Test
    void containsPlanets() {
    }
}