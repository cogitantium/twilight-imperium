package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.units.*;

public class TwilightImperium {

    public static void main(String[] args) {

        Player player01 = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player player02 = new Player("Pompey", "Federation of Sol", "Red");

        //player01/blue ships
        Dreadnought dreadnought01 = new Dreadnought(player01);
        Dreadnought dreadnought02 = new Dreadnought(player01);
        Destroyer destroyer01 = new Destroyer(player01);

        //create and add player01 ships to UnitList
        UnitList unitList01 = new UnitList();
        unitList01.add(dreadnought01); unitList01.add(dreadnought02); unitList01.add(destroyer01);

        //player02/red ships
        Cruiser cruiser01 = new Cruiser(player02);
        Cruiser cruiser02 = new Cruiser(player02);
        Carrier carrier01 = new Carrier(player02);

        //create and add player02 ships to UnitList
        UnitList unitList02 = new UnitList();
        unitList02.add(cruiser01); unitList02.add(cruiser02); unitList02.add(carrier01);

        //create planets with sensible resource production
        Planet mecatolRex = new Planet("Mecatol Rex", 6);
        Planet vegaMinor = new Planet("Vega Minor", 2);
        Planet vegaMajor = new Planet("Vega Major", 5);
        Planet industrex= new Planet("Industrex", 6);
        Planet rigelOne= new Planet("Rigel I", 2);
        Planet rigelTwo= new Planet("Rigel II", 4);
        Planet mirage= new Planet("Mirage", 3);

        //create PlanetLists
        PlanetList centerPlanets = new PlanetList(); centerPlanets.add(mecatolRex);
        PlanetList northPlanets = new PlanetList(); northPlanets.add(vegaMinor); northPlanets.add(vegaMajor);
        //omitting PlanetList northEastPlanets = new PlanetList();
        PlanetList southEastPlanets = new PlanetList(); southEastPlanets.add(industrex);
        PlanetList southPlanets = new PlanetList(); southPlanets.add(rigelOne); southPlanets.add(rigelTwo);
        // omitting PlanetList southWestPlanets = new PlanetList();
        PlanetList northWestPlanets = new PlanetList(); northWestPlanets.add(mirage);

        //create SystemTiles
        SystemTile centerSystem = new SystemTile(unitList01, centerPlanets);
        SystemTile northSystem = new SystemTile(unitList02, northPlanets);
        SystemTile northEastSystem = new SystemTile();
        SystemTile southEastSystem = new SystemTile(southEastPlanets);
        SystemTile southSystem = new SystemTile(southPlanets);
        SystemTile southWestSystem = new SystemTile();
        SystemTile northWestSystem = new SystemTile(northWestPlanets);

        //create SystemTileList and add all SystemTiles
        SystemTileList systemTileList01 = new SystemTileList();
        systemTileList01.add(centerSystem);
        systemTileList01.add(northSystem);
        systemTileList01.add(northEastSystem);
        systemTileList01.add(southEastSystem);
        systemTileList01.add(southSystem);
        systemTileList01.add(southWestSystem);
        systemTileList01.add(northWestSystem);

        //create galaxy
        Galaxy galaxy01 = new Galaxy(systemTileList01);
        galaxy01.getAllPlanetsInGalaxy();

    }

    /*
    public Galaxy buildGame() {

    }
    */
}
