package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

public class SystemTile {

    //abstracting postion by enumerating from 0 to 6
    public enum SystemPosition {
        c, n, ne, se, s, sw, nw;
    }

    private SystemPosition systemPosition;
    private UnitList shipsInSystem;
    private PlanetList planetsInSystem;

    public SystemTile(SystemPosition systemPosition, UnitList shipsInSystem, PlanetList planetsInSystem) {
        this.systemPosition = systemPosition;
        this.shipsInSystem = shipsInSystem;
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(SystemPosition systemPosition, PlanetList planetsInSystem) {
        this.systemPosition = systemPosition;
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(SystemPosition systemPosition) {
        this.systemPosition = systemPosition;
    }

    public boolean shipEnterSystemTile(Ships ship) {
        return this.shipsInSystem.add(ship);
    }

    public boolean shipLeaveSystemTile(Ships ship) {
        return this.shipsInSystem.remove(ship);
    }

    public UnitList getAllShipsInSystemTile() {
        return shipsInSystem;
    }

    public PlanetList getAllPlanetsInSystemTile() {
        return planetsInSystem;
    }
}
