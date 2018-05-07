package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.*;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;
import dk.aau.cs.fvejlb17.twilight.units.UnitSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Galaxy {

    private final SystemTileList systemTilesInGalaxy;

    Galaxy(SystemTileList systemTilesInGalaxy) {
        this.systemTilesInGalaxy = systemTilesInGalaxy;
    }

    private SystemTileList getAllSystemsInGalaxy() {
        return systemTilesInGalaxy;
    }

    //for all SystemTiles add all Ships to allShipsInGalaxy
    private UnitList getAllShipsInGalaxy() {
        UnitList allShipsInGalaxy = new UnitList();
        for (SystemTile systemTile : systemTilesInGalaxy) {
            allShipsInGalaxy.addAll(systemTile.getAllShipsInSystemTile());
        }
        return allShipsInGalaxy;
    }

    //for all SystemTiles add all Planets to allPlanetsInGalaxy
    private PlanetList getAllPlanetsInGalaxy() {
        PlanetList allPlanetsInGalaxy = new PlanetList();
        //for all SystemTiles, if SystemTile contains planets, add all planets and catch NPE if unexpected
        for (SystemTile systemTile : systemTilesInGalaxy) {
            try {
                if (systemTile.containsPlanets()) {
                    allPlanetsInGalaxy.addAll(systemTile.getAllPlanetsInSystemTile());
                }
            } catch (NullPointerException e) {
                System.out.println("ERROR: trying to add planets from empty PlanetList: " + e.getMessage());
            }
        }
        return allPlanetsInGalaxy;
    }

    //search galaxy for ship, if at least one equals, return true
    public boolean galaxyContainsShips(Ships ship) {
        for (SystemTile systemTile : this.getAllSystemsInGalaxy()) {
            if (systemTile.getAllShipsInSystemTile().contains(ship)) return true;
        }
        return false;
    }

    //search galaxy for planet, if contains, by equals method, return true
    public boolean galaxyContainsPlanet(Planet planet) {
        for (Planet srcPlanet : this.getAllPlanetsInGalaxy()) {
            if (srcPlanet.equals(planet)) return true;
        }
        return false;
    }

    private boolean containsDuplicatePlanets() throws GalaxyContainsDuplicatePlanetsException {
        //create hashset with initialCapacity of number of Planets in galaxy
        Set<Integer> planetHashcodes = new HashSet<>(getAllPlanetsInGalaxy().size());
        //for all Planets add to HashSet and throw exception if add-method returns false, implying duplicate
        for (Planet srcPlanet : this.getAllPlanetsInGalaxy()) {
            if (!planetHashcodes.add(srcPlanet.hashCode()))
                throw new GalaxyContainsDuplicatePlanetsException(srcPlanet.getPlanetName());
        }
        return true;
    }

    //naïve search for planet by planetName
    private boolean containsPlanet(String targetPlanetName) throws GalaxyDoesNotContainPlanetException {

        //create ArrayList of planetName and add all planetNames in Galaxy
        ArrayList<String> planetNames = new ArrayList<>();
        for (Planet srcPlanet : this.getAllPlanetsInGalaxy()) {
            planetNames.add(srcPlanet.getPlanetName());
        }
        //if galaxy contains targetPlanetName return true, else throw exception
        if (planetNames.contains(targetPlanetName)) return true;
        else throw new GalaxyDoesNotContainPlanetException(targetPlanetName);
    }

    //for any SystemTiles in Galaxy, return boolean of contains planets and is greater than maxNumOfPlanets
    private boolean numPlanetsInAnySystemTilesExceeds(int maxNumOfPlanets) throws GalaxyContainsSystemWithPlanetNumExceedingLimitException {
        for (SystemTile systemTile : this.systemTilesInGalaxy) {
            if (systemTile.containsPlanets() && systemTile.getNumOfPlanetsInSystemTile() > maxNumOfPlanets)
                throw new GalaxyContainsSystemWithPlanetNumExceedingLimitException(maxNumOfPlanets);
        }
        return false;
    }

    //check if geometry of all systems and their individual neighbourLists are congruent
    private boolean checkCardinalCongruencyOfSystemPositions() throws GalaxyCardinalPositionException {
        //create expected neighbours as SystemPositionList, omitting center system as case is handled independently
        SystemPositionList expectedNeighboursN = new SystemPositionListBuilder()
                .addNeighbour(SystemPosition.NW).addNeighbour(SystemPosition.NE)
                .addNeighbour(SystemPosition.C).build();
        SystemPositionList expectedNeighboursNE = new SystemPositionListBuilder()
                .addNeighbour(SystemPosition.N).addNeighbour(SystemPosition.SE)
                .addNeighbour(SystemPosition.C).build();
        SystemPositionList expectedNeighboursSE = new SystemPositionListBuilder()
                .addNeighbour(SystemPosition.NE).addNeighbour(SystemPosition.S)
                .addNeighbour(SystemPosition.C).build();
        SystemPositionList expectedNeighboursS = new SystemPositionListBuilder()
                .addNeighbour(SystemPosition.SE).addNeighbour(SystemPosition.SW)
                .addNeighbour(SystemPosition.C).build();
        SystemPositionList expectedNeighboursSW = new SystemPositionListBuilder()
                .addNeighbour(SystemPosition.S).addNeighbour(SystemPosition.NW)
                .addNeighbour(SystemPosition.C).build();
        SystemPositionList expectedNeighboursNW = new SystemPositionListBuilder()
                .addNeighbour(SystemPosition.SW).addNeighbour(SystemPosition.N)
                .addNeighbour(SystemPosition.C).build();

        //for all SystemTiles in Galaxy do
        for (SystemTile systemTile : this.systemTilesInGalaxy) {
            //switch on SystemPosition of SystemTile
            switch (systemTile.getSystemPosition()) {
                case C:
                    //for all SystemPositions in enum, do check
                    for (SystemPosition systemPosition : SystemPosition.values()) {
                        //if checked SystemPosition is not systemTile itself, do check
                        if (!(systemPosition == SystemPosition.C))
                            //if systemTile does not contain all but itself as neighbours, return false
                            if (systemTile.getNeighbourSystemTiles().contains(systemPosition)) break;
                            else return false;
                    }

                //check if system containsAll with expected neighbours, if true, break and continue, else return false
                case N:
                    if (systemTile.getNeighbourSystemTiles().containsAll(expectedNeighboursN)) break;
                    else throw new GalaxyCardinalPositionException(systemTile.getSystemPosition().toString());
                case NE:
                    if (systemTile.getNeighbourSystemTiles().containsAll(expectedNeighboursNE)) break;
                    else throw new GalaxyCardinalPositionException();
                case SE:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursSE)) break;
                    else throw new GalaxyCardinalPositionException();
                case S:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursS)) break;
                    else throw new GalaxyCardinalPositionException();
                case SW:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursSW)) break;
                    else throw new GalaxyCardinalPositionException();
                case NW:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursNW)) break;
                    else throw new GalaxyCardinalPositionException();
                //if no case is matched, we've gotten an unknown system
                default:
                    throw new GalaxyCardinalPositionException("FATAL", "Unknown SystemPosition encountered!");
            }
        }
        return true;
    }

    //tie all relevant Galaxy-methods together for legality check of defined properties
    public boolean checkGalaxyLegality() {

        //assume all properties are false and try/catch each property through method calls
        boolean containsMecatolRex = false;
        try {
            containsMecatolRex = containsPlanet("Mecatol Rex");
        } catch (GalaxyDoesNotContainPlanetException e) {
            System.out.println(e.getMessage());
        }
        boolean noDuplicatePlanets = false;
        try {
            noDuplicatePlanets = containsDuplicatePlanets();
        } catch (GalaxyContainsDuplicatePlanetsException e) {
            System.out.println(e.getMessage());
        }

        //assuming opposite proposition, as method checks whether any system exceeds
        boolean noSystemExceedsThreePlanets = true;
        try {
            noSystemExceedsThreePlanets = numPlanetsInAnySystemTilesExceeds(3);
        } catch (GalaxyContainsSystemWithPlanetNumExceedingLimitException e) {
            System.out.println(e.getMessage());
        }

        boolean congruentCardinalPositions = false;
        try {
            congruentCardinalPositions = checkCardinalCongruencyOfSystemPositions();
        } catch (GalaxyCardinalPositionException e) {
            System.out.println(e.getMessage());
        }

        //utilising short-circuiting for returning appropriate boolean to proposition
        return containsMecatolRex && noDuplicatePlanets && !noSystemExceedsThreePlanets && congruentCardinalPositions;
    }

    //add all ships owned by player to temp UnitList and sorts with UnitSort
    public UnitList getAllShipsInGalaxyByOwnerSorted(Player player) {
        UnitList unitList = new UnitList();
        //add all ships in galaxy owned by player to temp UnitList
        for (Ships ship : this.getAllShipsInGalaxy()) {
            if (ship.getOwner().equals(player)) unitList.add(ship);
        }
        //call sort with comparator UnitSort and return list
        unitList.sort(new UnitSort());
        return unitList;
    }
}