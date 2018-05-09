package dk.aau.cs.fvejlb17.twilight.systems;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.players.PlayerList;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;
import dk.aau.cs.fvejlb17.twilight.units.UnitListBuilder;
import dk.aau.cs.fvejlb17.twilight.units.comparators.UnitResourceComparator;

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
        calculatePlanetaryControl();
        this.planetsInSystem = planetsInSystem;
    }

    public SystemTile(UnitList unitList) {
        this.shipsInSystem = unitList;
        calculatePlanetaryControl();
    }

    public SystemTile(SystemPosition systemPosition) {
        this.systemPosition = systemPosition;
        calculateAndSetNeighbourPositions();
    }

    public SystemTile(PlanetList planetsInSystem) {
        this.planetsInSystem = planetsInSystem;
    }

    public Player resolveSpaceBattle(Player playerOne, Player playerTwo) {

        //if no ships reside in system, a space battle cannot occur
        if (shipsInSystem.isEmpty()) return null;

        //check if space battle can occur, this happens when two and only two players have ships in the same system
        //initialise variable to 1, as we know SystemTile is not empty, thus at least one player with ships exists
        int numOfPlayersWithShipsInSystem = 1;

        //create PlayerList and add first ship's owner to list
        PlayerList playerList = new PlayerList();
        playerList.add(this.getAllShipsInSystemTile().get(0).getOwner());

        for (Ships ship : this.getAllShipsInSystemTile()) {
            //if ship encountered in system belongs to neither player supplied,
            //then space battle cannot occur between players supplied, return null
            if (!(ship.getOwner() == playerOne || ship.getOwner() == playerTwo)) return null;
            //if playerList doesn't contain current ships owner, add owner to playerList and increment running tally
            if (!playerList.contains(ship.getOwner())) {
                playerList.add(ship.getOwner());
                numOfPlayersWithShipsInSystem++;
            }
        }

        //if numOfPlayersWithShipsInSystem is different from 2, space battle cannot happen, return null
        if (!(numOfPlayersWithShipsInSystem == 2)) return null;

        //as space battle is imminent, find and create UnitList of each player's ships from this system
        UnitList playerOneFleet = getAllShipsInSystemOwnedBy(playerOne);
        UnitList playerTwoFleet = getAllShipsInSystemOwnedBy(playerTwo);

        //sort UnitLists of player's fleets in ascending resourceCost, anticipating destroyed ships
        playerOneFleet.sort(new UnitResourceComparator());
        playerTwoFleet.sort(new UnitResourceComparator());

        //conduct space battle while both players has fleets
        while (!playerOneFleet.isEmpty() && !playerTwoFleet.isEmpty()) {

            //roll hits for both players
            int playerOneHits = playerOneFleet.getNumHitsFromFleet();
            int playerTwoHits = playerTwoFleet.getNumHitsFromFleet();

            //for any hits playerOne got and if playerTwo still has a fleet, remove destroyed ships
            for (int i = 0; i < playerOneHits && !playerTwoFleet.isEmpty(); i++) {
                //utilise leave-method to remove ship from system with lowest resourceCost
                // and recalculate controlling player state on-the-fly
                this.shipLeaveSystemTile(playerTwoFleet.get(0));
                //remove method local copy of ship
                playerTwoFleet.remove(0);
            }

            //for any hits playerOne got and if playerTwo still has a fleet, remove destroyed ships
            for (int i = 0; i < playerTwoHits && !playerOneFleet.isEmpty(); i++) {
                //utilise leave-method to remove ship from system with lowest resourceCost
                // and recalculate controlling player state on-the-fly
                this.shipLeaveSystemTile(playerOneFleet.get(0));
                //remove method local copy of ship
                playerOneFleet.remove(0);
            }
        }

        //if both player's fleets are destroyed, no victor has emerged
        if (playerOneFleet.isEmpty() && playerTwoFleet.isEmpty()) return null;

        //if playerOne's fleet is empty, playerTwo must've won, or vice versa
        if (playerOneFleet.isEmpty()) return playerTwo;
        else return playerOne;
    }

    //for all ships in system, if owner equals parameter player, build UnitList and return built UnitList
    public UnitList getAllShipsInSystemOwnedBy(Player player) {
        UnitListBuilder unitListBuilder = new UnitListBuilder();
        for (Ships ship : this.shipsInSystem) {
            if (ship.getOwner().equals(player)) unitListBuilder.addUnit(ship);
        }
        return unitListBuilder.build();
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
            //since ships has been modified in SystemTile, planetary control is subject to change and must be recalculated
            this.calculatePlanetaryControl();
            return true;
        }
        return false;
    }

    public boolean shipLeaveSystemTile(Ships ship) {
        if (this.shipsInSystem.remove(ship)) {
            //since ships has been modified in SystemTile, planetary control is subject to change and must be recalculated
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
