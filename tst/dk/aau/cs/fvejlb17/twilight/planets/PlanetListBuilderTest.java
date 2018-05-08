package dk.aau.cs.fvejlb17.twilight.planets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetListBuilderTest {

    @Test
    void addPlanet() {
        PlanetList planetList01;
        PlanetList planetList02 = new PlanetList();
        PlanetListBuilder planetListBuilder01 = new PlanetListBuilder();

        Planet planet01 = new Planet("planetName", 6);

        planetList01 = planetListBuilder01.addPlanet(planet01).build();
        planetList02.add(planet01);

        assertEquals(planetList01.get(0), planetList02.get(0));
    }

}