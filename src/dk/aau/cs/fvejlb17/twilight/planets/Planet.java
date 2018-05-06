package dk.aau.cs.fvejlb17.twilight.planets;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return getResourceProduction() == planet.getResourceProduction() &&
                Objects.equals(getPlanetName(), planet.getPlanetName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlanetName(), getResourceProduction());
    }
}
