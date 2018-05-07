package dk.aau.cs.fvejlb17.twilight.planets;

import dk.aau.cs.fvejlb17.twilight.players.Player;

import java.util.Objects;

public class Planet {

    private final String planetName;
    private final int resourceProduction;
    //controlling player initialised to null - given more time, this would be avoided
    private Player controllingPlayer = null;

    public Planet(String planetName, int resourceProduction) {
        this.planetName = planetName;
        this.resourceProduction = resourceProduction;
    }

    public String getPlanetName() {
        return planetName;
    }

    private int getResourceProduction() {
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

    public Player getControllingPlayer() {
        return controllingPlayer;
    }

    public void setControllingPlayer(Player controllingPlayer) {
        this.controllingPlayer = controllingPlayer;
    }
}
