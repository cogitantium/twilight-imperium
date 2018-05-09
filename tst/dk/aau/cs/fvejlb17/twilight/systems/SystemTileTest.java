package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.galaxies.GalaxyCreator;
import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.units.Cruiser;
import dk.aau.cs.fvejlb17.twilight.units.Dreadnought;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;
import dk.aau.cs.fvejlb17.twilight.units.UnitListBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemTileTest {

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
    void calculatePlanetaryControl() {
        //create sensible preset game and assert legality
        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();
        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player playerPompey = new Player("Pompey", "Federation of Sol", "Red");
        assertTrue(presetGalaxy.checkGalaxyLegality());

        SystemTileList systemTileList = presetGalaxy.getAllSystemsInGalaxy();

        //for all systems in preset game
        for (SystemTile systemTile : systemTileList) {
            //for all planets in SystemTileList
            for (Planet planet : systemTile.getAllPlanetsInSystemTile()) {
                switch (systemTile.getSystemPosition()) {
                    //if SystemTile is located in C, controlling player is expected to be Crassus
                    case C:
                        assertTrue(planet.getControllingPlayer().equals(playerCrassus));
                        break;
                    //if SystemTile is located in N, controlling player is expected to be Pompey
                    case N:
                        assertTrue(planet.getControllingPlayer().equals(playerPompey));
                        break;
                    //if SystemTile is not located in C or N, controlling player is expected to be null
                    default:
                        assertTrue(planet.getControllingPlayer() == null);
                        break;
                }
            }
        }
    }

    @Test
    void resolveUnfairSpaceBattle1() {
        Player playerOne = new Player("playerNameOne", "playerRace", "playerColour");
        Player playerTwo = new Player("playerNameTwo", "playerRace", "playerColour");

        Dreadnought dreadnought01 = new Dreadnought(playerOne);
        Dreadnought dreadnought02 = new Dreadnought(playerOne);
        Dreadnought dreadnought03 = new Dreadnought(playerOne);
        Dreadnought dreadnought04 = new Dreadnought(playerOne);
        Dreadnought dreadnought05 = new Dreadnought(playerOne);
        Dreadnought dreadnought06 = new Dreadnought(playerOne);
        Dreadnought dreadnought07 = new Dreadnought(playerOne);
        Dreadnought dreadnought08 = new Dreadnought(playerTwo);
        UnitList unitList = new UnitListBuilder().addUnit(dreadnought01).addUnit(dreadnought02)
                .addUnit(dreadnought03).addUnit(dreadnought04).addUnit(dreadnought05)
                .addUnit(dreadnought06).addUnit(dreadnought07).addUnit(dreadnought08).build();

        SystemTile systemTile = new SystemTile(unitList);

        //as playerTwo by first round has a chance to win: (6/10 * 1) / (6/10 * 7),
        // it's very unlikely that playerTwo would win, assert true that playerOne wins
        assertEquals(playerOne, systemTile.resolveSpaceBattle(playerOne, playerTwo));

        //if playerTwo lost, no ships owned by playerTwo should exist in system
        assertTrue(systemTile.getAllShipsInSystemOwnedBy(playerTwo).isEmpty());
    }

    @Test
    void resolveUnfairSpaceBattle2() {
        Player playerOne = new Player("playerNameOne", "playerRace", "playerColour");
        Player playerTwo = new Player("playerNameTwo", "playerRace", "playerColour");

        Dreadnought dreadnought01 = new Dreadnought(playerOne);
        Dreadnought dreadnought02 = new Dreadnought(playerOne);

        UnitList unitList = new UnitListBuilder().addUnit(dreadnought01).addUnit(dreadnought02).build();

        SystemTile systemTile = new SystemTile(unitList);

        //since only playerOne has ships in system, victorious player should be null
        assertNull(systemTile.resolveSpaceBattle(playerOne, playerTwo));
    }
}