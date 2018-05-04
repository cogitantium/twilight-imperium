package dk.aau.cs.fvejlb17.twilight.planets;

public class Planet {

    private String planetName;
    private int resourceProduction;

    public Planet(String planetName, int resourceProduction) {
        this.planetName = planetName;
        this.resourceProduction = resourceProduction;
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }
}
