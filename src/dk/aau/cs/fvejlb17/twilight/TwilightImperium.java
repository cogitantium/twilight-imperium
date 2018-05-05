package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetListBuilder;
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

        //create and add player01 ships to UnitList with builder
        UnitList unitList01 = new UnitListBuilder().addUnit(dreadnought01).addUnit(dreadnought02).addUnit(destroyer01).build();

        //player02/red ships
        Cruiser cruiser01 = new Cruiser(player02);
        Cruiser cruiser02 = new Cruiser(player02);
        Carrier carrier01 = new Carrier(player02);

        //create and add player02 ships to UnitList
        UnitList unitList02 = new UnitListBuilder().addUnit(cruiser01).addUnit(cruiser02).addUnit(carrier01).build();

        //create planets with sensible resource production
        Planet mecatolRex = new Planet("Mecatol Rex", 6);
        Planet vegaMinor = new Planet("Vega Minor", 2);
        Planet vegaMajor = new Planet("Vega Major", 5);
        Planet industrex = new Planet("Industrex", 6);
        Planet rigelOne = new Planet("Rigel I", 2);
        Planet rigelTwo = new Planet("Rigel II", 4);
        Planet mirage = new Planet("Mirage", 3);

        //create PlanetLists
        PlanetList centerPlanets = new PlanetListBuilder().addPlanet(mecatolRex).build();
        PlanetList northPlanets = new PlanetListBuilder().addPlanet(vegaMinor).addPlanet(vegaMajor).build();
        PlanetList northEastPlanets = new PlanetListBuilder().build();
        PlanetList southEastPlanets = new PlanetListBuilder().addPlanet(industrex).build();
        PlanetList southPlanets = new PlanetListBuilder().addPlanet(rigelOne).addPlanet(rigelTwo).build();
        PlanetList southWestPlanets = new PlanetListBuilder().build();
        PlanetList northWestPlanets = new PlanetListBuilder().addPlanet(mirage).build();

        //create SystemTiles
        SystemTile centerSystem = new SystemTile(SystemTile.SystemPosition.c, unitList01, centerPlanets);
        SystemTile northSystem = new SystemTile(SystemTile.SystemPosition.n, unitList02, northPlanets);
        SystemTile northEastSystem = new SystemTile(SystemTile.SystemPosition.ne);
        SystemTile southEastSystem = new SystemTile(SystemTile.SystemPosition.se, southEastPlanets);
        SystemTile southSystem = new SystemTile(SystemTile.SystemPosition.s, southPlanets);
        SystemTile southWestSystem = new SystemTile(SystemTile.SystemPosition.sw);
        SystemTile northWestSystem = new SystemTile(SystemTile.SystemPosition.nw, northWestPlanets);

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
        System.out.println("Galaxy contains industrex: " + galaxy01.galaxyContainsShips(dreadnought01));

    }

}
