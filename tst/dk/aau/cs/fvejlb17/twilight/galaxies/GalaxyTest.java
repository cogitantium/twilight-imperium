package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GalaxyTest {

    @Test
    void checkGalaxyLegality() {
        Galaxy galaxy = new GalaxyCreator().createPresetGame();
        assertTrue(galaxy.checkGalaxyLegality());
    }

    @Test
    void getPlayersInGalaxy() {
        //test presetGames players are actually present in constructed galaxy
        Galaxy galaxy = new GalaxyCreator().createPresetGame();

        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player playerPompey = new Player("Pompey", "Federation of Sol", "Red");

        assertTrue(galaxy.getPlayersInGalaxy().contains(playerCrassus)
                && galaxy.getPlayersInGalaxy().contains(playerPompey));
    }

    @Test
    void getAllSystemsInGalaxy() {
        Galaxy galaxy = new GalaxyCreator().createPresetGame();

        //assert true that num of presetGame SystemTiles are actually present in constructed galaxy
        assertTrue(galaxy.getAllSystemsInGalaxy().size() == 7);
    }

    @Test
    void toStringTest() {
        Galaxy galaxy = new GalaxyCreator().createPresetGame();

        assertEquals("Galaxy{systemTilesInGalaxy=7, playersInGalaxy=2}", galaxy.toString());
    }
}