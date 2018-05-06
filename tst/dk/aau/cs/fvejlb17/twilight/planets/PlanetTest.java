package dk.aau.cs.fvejlb17.twilight.planets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void getPlanetName() {
    }

    @Test
    void equalsTest() {
        Planet planet01 = new Planet("planetName", 6);
        Planet planet02 = new Planet("planetName", 6);

        assertTrue(planet01.equals(planet02));
        assertTrue(planet02.equals(planet01));
    }

    @Test
    void hashCodeTest() {
        Planet planet01 = new Planet("planetName", 6);
        Planet planet02 = new Planet("planetName", 6);

        assertEquals(planet01.hashCode(), planet02.hashCode());
    }
}