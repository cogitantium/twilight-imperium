package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTilePositionList;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTilePositionListBuilder;
import dk.aau.cs.fvejlb17.twilight.units.Ships;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Galaxy {

    private SystemTileList systemTilesInGalaxy;

    public Galaxy(SystemTileList systemTilesInGalaxy) {
        this.systemTilesInGalaxy = systemTilesInGalaxy;
    }

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

    public boolean containsDuplicatePlanets() {
        //create hashset with initialCapacity of number of Planets in galaxy
        Set<Integer> planetHashcodes = new HashSet<>(getAllPlanetsInGalaxy().size());
        //for all Planets add to HashSet and return true, if add-method returns false, implying duplicate
        for (Planet srcPlanet : this.getAllPlanetsInGalaxy()) {
            if (!planetHashcodes.add(srcPlanet.hashCode())) return true;
        }
        return false;
    }

    //naïve search for planet by planetName
    public boolean containsPlanet(String targetPlanetName) {
        //create ArrayList of planetName and add all planetNames in Galaxy
        ArrayList<String> planetNames = new ArrayList<>();
        for (Planet srcPlanet : this.getAllPlanetsInGalaxy()) {
            planetNames.add(srcPlanet.getPlanetName());
        }
        return planetNames.contains(targetPlanetName);
    }

    //for any SystemTiles in Galaxy, return boolean of contains planets and is greater than maxNumOfPlanets
    public boolean numPlanetsInAnySystemTilesExceeds(int maxNumOfPlanets) {
        for (SystemTile systemTile : this.systemTilesInGalaxy) {
            if (systemTile.containsPlanets() && systemTile.getNumOfPlanetsInSystemTile() > maxNumOfPlanets) return true;
        }
        return false;
    }

    public boolean checkCardinalCongruencyOfSystemPositions() {
        //create expected neighbours as SystemTilePositionList, omitting center system as case is handled independently
        SystemTilePositionList expectedNeighboursN = new SystemTilePositionListBuilder()
                .addNeighbour(SystemTile.SystemPosition.NW).addNeighbour(SystemTile.SystemPosition.NE)
                .addNeighbour(SystemTile.SystemPosition.C).build();
        SystemTilePositionList expectedNeighboursNE = new SystemTilePositionListBuilder()
                .addNeighbour(SystemTile.SystemPosition.N).addNeighbour(SystemTile.SystemPosition.SE)
                .addNeighbour(SystemTile.SystemPosition.C).build();
        SystemTilePositionList expectedNeighboursSE = new SystemTilePositionListBuilder()
                .addNeighbour(SystemTile.SystemPosition.NE).addNeighbour(SystemTile.SystemPosition.S)
                .addNeighbour(SystemTile.SystemPosition.C).build();
        SystemTilePositionList expectedNeighboursS = new SystemTilePositionListBuilder()
                .addNeighbour(SystemTile.SystemPosition.SE).addNeighbour(SystemTile.SystemPosition.SW)
                .addNeighbour(SystemTile.SystemPosition.C).build();
        SystemTilePositionList expectedNeighboursSW = new SystemTilePositionListBuilder()
                .addNeighbour(SystemTile.SystemPosition.S).addNeighbour(SystemTile.SystemPosition.NW)
                .addNeighbour(SystemTile.SystemPosition.C).build();
        SystemTilePositionList expectedNeighboursNW = new SystemTilePositionListBuilder()
                .addNeighbour(SystemTile.SystemPosition.SW).addNeighbour(SystemTile.SystemPosition.N)
                .addNeighbour(SystemTile.SystemPosition.C).build();

        //for all SystemTiles in Galaxy do
        for (SystemTile systemTile : this.systemTilesInGalaxy) {
            //switch on SystemPosition of SystemTile
            switch (systemTile.getSystemPosition()) {
                case C:
                    //for all SystemPositions in enum, do check
                    for (SystemTile.SystemPosition systemPosition : SystemTile.SystemPosition.values()) {
                        //if checked SystemPosition is not systemTile itself, do check
                        if (!(systemPosition == SystemTile.SystemPosition.C))
                            //if systemTile does not contain all but itself as neighbours, return false
                            if (systemTile.getNeighbourSystemTiles().contains(systemPosition)) break; else return false;
                    }

                //check if system containsAll with expected neighbours, if true, break and continue, else return false
                case N:
                    if (systemTile.getNeighbourSystemTiles().containsAll(expectedNeighboursN)) break; else return false;
                case NE:
                    if (systemTile.getNeighbourSystemTiles().containsAll(expectedNeighboursNE)) break; else return false;
                case SE:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursSE)) break; else return false;
                case S:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursS)) break; else return false;
                case SW:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursSW)) break; else return false;
                case NW:
                    if (systemTile.getNeighbourSystemTiles().equals(expectedNeighboursNW)) break; else return false;
                //if no case is matched, we've gotten an unknown system
                default: return false;
            }
        }
        return true;
    }
    //TODO sort function

}
