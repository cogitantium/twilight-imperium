package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

public class SystemTile {

    private UnitList shipsInSystem;
    private PlanetList planetsInSystem;

    public SystemTile(UnitList shipsInSystem, PlanetList planetsInSystem) {
        this.shipsInSystem = shipsInSystem;
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(PlanetList planetsInSystem) {
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile() {}

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
