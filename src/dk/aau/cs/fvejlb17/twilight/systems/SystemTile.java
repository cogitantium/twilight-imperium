package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

public class SystemTile {

    private final SystemPosition systemPosition;
    private SystemTilePositionList neighbourSystemTiles;
    private UnitList shipsInSystem;
    private PlanetList planetsInSystem;

    public SystemTile(SystemPosition systemPosition, UnitList shipsInSystem, PlanetList planetsInSystem) {
        this.systemPosition = systemPosition; calculateAndSetNeighbourPositions();
        this.shipsInSystem = shipsInSystem;
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(SystemPosition systemPosition, PlanetList planetsInSystem) {
        this.systemPosition = systemPosition; calculateAndSetNeighbourPositions();
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(SystemPosition systemPosition) {
        this.systemPosition = systemPosition; calculateAndSetNeighbourPositions();
    }

    public SystemPosition getSystemPosition() {
        return this.systemPosition;
    }

    public SystemTilePositionList getNeighbourSystemTiles() {
        return this.neighbourSystemTiles;
    }

    //calculate and return neighbours from SystemTile SystemPosition
    private void calculateAndSetNeighbourPositions() {
        switch (this.systemPosition) {
            case C: this.neighbourSystemTiles = new SystemTilePositionListBuilder().addAllNeighbours().build(); break;
            case N: this.neighbourSystemTiles = new SystemTilePositionListBuilder()
                    .addNeighbour(SystemPosition.NW).addNeighbour(SystemPosition.NE).build(); break;
            case NE: this.neighbourSystemTiles = new SystemTilePositionListBuilder()
                    .addNeighbour(SystemPosition.N).addNeighbour(SystemPosition.SE).build(); break;
            case SE: this.neighbourSystemTiles = new SystemTilePositionListBuilder()
                    .addNeighbour(SystemPosition.NE).addNeighbour(SystemPosition.S).build(); break;
            case S: this.neighbourSystemTiles = new SystemTilePositionListBuilder()
                    .addNeighbour(SystemPosition.SE).addNeighbour(SystemPosition.SW).build(); break;
            case SW: this.neighbourSystemTiles = new SystemTilePositionListBuilder()
                    .addNeighbour(SystemPosition.S).addNeighbour(SystemPosition.NW).build(); break;
            case NW: this.neighbourSystemTiles = new SystemTilePositionListBuilder()
                    .addNeighbour(SystemPosition.SW).addNeighbour(SystemPosition.N).build(); break;
        }
        //if not center system, add center as neighbour without builder
        if (!(this.systemPosition == SystemPosition.C)) this.neighbourSystemTiles.add(SystemPosition.C);
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

    public int getNumOfPlanetsInSystemTile() {
        return planetsInSystem.size();
    }

    public boolean containsPlanets() {
        //returns true if reference to PlanetList exists and array contains some object
        return (planetsInSystem != null && !planetsInSystem.isEmpty());
    }
}
