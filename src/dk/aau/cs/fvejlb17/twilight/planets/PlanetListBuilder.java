package dk.aau.cs.fvejlb17.twilight.planets;

public class PlanetListBuilder {

    private final PlanetList planetList = new PlanetList();

    public PlanetListBuilder addPlanet(Planet planet) {
        this.planetList.add(planet);
        return this;
    }

    public PlanetList build() {
        return planetList;
    }
}
