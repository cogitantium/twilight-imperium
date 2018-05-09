package dk.aau.cs.fvejlb17.twilight.galaxies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GalaxyContainsSystemWithPlanetNumExceedingLimitExceptionTest {

    @Test
    void exceptionTesting() throws GalaxyContainsSystemWithPlanetNumExceedingLimitException {
        //create a galaxy where it is known that a system has more than 2 planets
        Galaxy galaxy = new GalaxyCreator().createPresetGame();
        galaxy.numPlanetsInAnySystemTilesExceeds(2);

        //assert that exception is thrown
        Throwable exception = assertThrows(GalaxyContainsSystemWithPlanetNumExceedingLimitException.class, () -> {
            throw new GalaxyContainsSystemWithPlanetNumExceedingLimitException(2);
        });
        //assert that exception has expected message
        assertEquals("ERROR: A SystemTile in Galaxy contains more planets than: 2", exception.getMessage());
    }
}