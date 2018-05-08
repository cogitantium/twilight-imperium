package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

import java.util.ArrayList;

public class SystemTile {

    private SystemPosition systemPosition;
    private SystemPositionList neighbourSystemTiles = new SystemPositionList();
    private UnitList shipsInSystem = new UnitList();
    private PlanetList planetsInSystem = new PlanetList();

    public SystemTile(SystemPosition systemPosition, UnitList shipsInSystem, PlanetList planetsInSystem) {
        this.systemPosition = systemPosition;
        calculateAndSetNeighbourPositions();
        this.shipsInSystem = shipsInSystem;
        //for all shipsInSystem, set their SystemPosition to systemPosition
        for (Ships ship : this.shipsInSystem) {
            ship.setSystemPosition(systemPosition);
        }
        this.planetsInSystem = planetsInSystem;
        //since ships has been added to SystemTile, planetary control is relevant and must be calculated
        calculatePlanetaryControl();
    }

    public SystemTile(SystemPosition systemPosition, PlanetList planetsInSystem) {
        this.systemPosition = systemPosition;
        calculateAndSetNeighbourPositions();
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(UnitList unitList, PlanetList planetsInSystem) {
        this.shipsInSystem = unitList;
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(SystemPosition systemPosition) {
        this.systemPosition = systemPosition;
        calculateAndSetNeighbourPositions();
    }

    public SystemTile(PlanetList planetsInSystem) {
        this.planetsInSystem = planetsInSystem;
    }

    public SystemPosition getSystemPosition() {
        return this.systemPosition;
    }

    public void setSystemPosition(SystemPosition systemPosition) {
        this.systemPosition = systemPosition;
        calculateAndSetNeighbourPositions();
    }

    public SystemPositionList getNeighbourSystemTiles() {
        return this.neighbourSystemTiles;
    }

    //calculate and return neighbours from SystemTile SystemPosition
    private void calculateAndSetNeighbourPositions() {
        switch (this.systemPosition) {
            case C:
                this.neighbourSystemTiles = new SystemPositionListBuilder().addAllNeighbours().build();
                break;
            case N:
                this.neighbourSystemTiles = new SystemPositionListBuilder()
                        .addNeighbour(SystemPosition.NW).addNeighbour(SystemPosition.NE).build();
                break;
            case NE:
                this.neighbourSystemTiles = new SystemPositionListBuilder()
                        .addNeighbour(SystemPosition.N).addNeighbour(SystemPosition.SE).build();
                break;
            case SE:
                this.neighbourSystemTiles = new SystemPositionListBuilder()
                        .addNeighbour(SystemPosition.NE).addNeighbour(SystemPosition.S).build();
                break;
            case S:
                this.neighbourSystemTiles = new SystemPositionListBuilder()
                        .addNeighbour(SystemPosition.SE).addNeighbour(SystemPosition.SW).build();
                break;
            case SW:
                this.neighbourSystemTiles = new SystemPositionListBuilder()
                        .addNeighbour(SystemPosition.S).addNeighbour(SystemPosition.NW).build();
                break;
            case NW:
                this.neighbourSystemTiles = new SystemPositionListBuilder()
                        .addNeighbour(SystemPosition.SW).addNeighbour(SystemPosition.N).build();
                break;
        }
        //if not center system, add center as neighbour without builder
        if (!(this.systemPosition == SystemPosition.C)) this.neighbourSystemTiles.add(SystemPosition.C);
    }

    private void calculatePlanetaryControl() {
        //if no ships are present in system, no player has planetary control
        if (this.shipsInSystem.isEmpty()) return;

        //create array of unique players in SystemTile
        ArrayList<Player> playerList = new ArrayList<>();
        for (Ships ship : this.shipsInSystem) {
            //if playerList does not contain ships owner, add owner to list
            if (!playerList.contains(ship.getOwner())) playerList.add(ship.getOwner());
        }

        //if only one players ship(s) reside in SystemTile, that player has planetary control
        if (playerList.size() == 1) {
            //for all planets in system, set controlling player to first ships owner
            for (Planet planet : this.planetsInSystem) {
                planet.setControllingPlayer(shipsInSystem.get(0).getOwner());
            }
            //if no player has ship(s) in system or more players has ship(s) in system, no player has planetary control
        } else if (playerList.size() > 1 || playerList.size() == 0) {
            //for all planets in system, set controlling player to null - given more time, this would be avoided
            for (Planet planet : this.planetsInSystem) {
                planet.setControllingPlayer(null);
            }
        }
    }

    public boolean shipEnterSystemTile(Ships ship) {
        if (this.shipsInSystem.add(ship)) {
            //since ships has modified in SystemTile, planetary control is subject to change and must be recalculated
            this.calculatePlanetaryControl();
            return true;
        }
        return false;
    }

    public boolean shipLeaveSystemTile(Ships ship) {
        if (this.shipsInSystem.remove(ship)) {
            //since ships has modified in SystemTile, planetary control is subject to change and must be recalculated
            this.calculatePlanetaryControl();
            return true;
        }
        return false;
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
