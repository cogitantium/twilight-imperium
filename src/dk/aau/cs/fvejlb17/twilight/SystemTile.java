package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.units.Ships;

import java.util.List;

public abstract class SystemTile {

    private List<SystemTile> neightborSystemTiles;
    private List<Ships> shipsInSystem;
    private List<Planet> planetsInSystem;

    protected SystemTile(List<SystemTile> neightborSystemTiles, List<Ships> shipsInSystem, List<Planet> planetsInSystem) {
        this.neightborSystemTiles = neightborSystemTiles;
        this.shipsInSystem = shipsInSystem;
        this.planetsInSystem = planetsInSystem;
    }

    public boolean shipEnterSystem(Ships ship) {
        return this.shipsInSystem.add(ship);
    }

    public boolean shipLeaveSystem(Ships ship) {
        return this.shipsInSystem.remove(ship);
    }

    public List<Ships> getAllShipsInSystem() {
        return this.shipsInSystem;
    }
}
