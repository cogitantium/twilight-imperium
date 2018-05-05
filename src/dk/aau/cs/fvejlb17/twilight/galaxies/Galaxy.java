package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

public class Galaxy {

    private SystemTileList systemTilesInGalaxy;

    public Galaxy(SystemTileList systemTilesInGalaxy) {
        this.systemTilesInGalaxy = systemTilesInGalaxy;
    }

    public Galaxy() {}

    public SystemTileList getAllSystemsInGalaxy() {
        return systemTilesInGalaxy;
    }

    //for all SystemTiles add all Ships to allShipsInGalaxy
    public UnitList getAllShipsInGalaxy() {
        UnitList allShipsInGalaxy = new UnitList();
        for (SystemTile systemTile : systemTilesInGalaxy) {
            allShipsInGalaxy.addAll(systemTile.getAllShipsInSystemTile());
        }
        return allShipsInGalaxy;
    }

    //for all SystemTiles add all Planets to allPlanetsInGalaxy
    public PlanetList getAllPlanetsInGalaxy() {
        PlanetList allPlanetsInGalaxy = new PlanetList();
        try {
            for (SystemTile systemTile : systemTilesInGalaxy) {
                allPlanetsInGalaxy.addAll(systemTile.getAllPlanetsInSystemTile());
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: SystemTiles in Galaxy are " + e.getMessage());
        }
        return allPlanetsInGalaxy;
    }

    public boolean galaxyContainsShips(Ships ship) {
        for (SystemTile st : this.getAllSystemsInGalaxy()) {
            if (st.getAllShipsInSystemTile().contains(ship)) return true;
        }
        return false;
    }
}
