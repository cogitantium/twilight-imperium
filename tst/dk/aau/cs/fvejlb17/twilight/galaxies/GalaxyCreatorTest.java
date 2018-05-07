package dk.aau.cs.fvejlb17.twilight.galaxies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyCreatorTest {

    @Test
    void createPresetGame() {
        Galaxy galaxy01 = new GalaxyCreator().createPresetGame();

        assertTrue(galaxy01.checkGalaxyLegality());
    }
}