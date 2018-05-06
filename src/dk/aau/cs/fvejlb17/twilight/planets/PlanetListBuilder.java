package dk.aau.cs.fvejlb17.twilight.planets;

public class PlanetListBuilder {

    private final PlanetList planetList = new PlanetList();

    public PlanetListBuilder addPlanet(Planet planet) {
        try {
            this.planetList.add(planet);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return this;
    }

    public PlanetList build() {
        return planetList;
    }
}
