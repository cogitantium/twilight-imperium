package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

import java.util.List;

public class SystemTile {

    private UnitList shipsInSystem;
    private List<Planet> planetsInSystem;

    protected SystemTile( UnitList shipsInSystem, List<Planet> planetsInSystem) {
        this.shipsInSystem = shipsInSystem;
        this.planetsInSystem = planetsInSystem;
    }

    public boolean shipEnterSystem(Ships ship) {
        return this.shipsInSystem.add(ship);
    }

    public boolean shipLeaveSystem(Ships ship) {
        return this.shipsInSystem.remove(ship);
    }

     public UnitList getAllShipsInSystem() {
        return this.shipsInSystem;
    }
}
