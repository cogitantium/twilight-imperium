package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetListBuilder;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileListBuilder;
import dk.aau.cs.fvejlb17.twilight.units.*;

public class GalaxyCreator {

    public Galaxy createPresetGame() {

        //create players from preset definitions
        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player playerPompey = new Player("Pompey", "Federation of Sol", "Red");

        //create playerCrassus' ships and add to UnitList
        Dreadnought dreadnought01 = new Dreadnought(playerCrassus);
        Dreadnought dreadnought02 = new Dreadnought(playerCrassus);
        Destroyer destroyer01 = new Destroyer(playerCrassus);
        UnitList unitList01 = new UnitListBuilder().addUnit(dreadnought01).addUnit(dreadnought02).addUnit(destroyer01).build();

        //create playerPompey's ships and add to UnitList
        Cruiser cruiser01 = new Cruiser(playerPompey);
        Cruiser cruiser02 = new Cruiser(playerPompey);
        Carrier carrier01 = new Carrier(playerPompey);
        UnitList unitList02 = new UnitListBuilder().addUnit(cruiser01).addUnit(cruiser02).addUnit(carrier01).build();

        //create planets with arbitrary but sensible resource production
        Planet mecatolRex = new Planet("Mecatol Rex", 5);
        Planet vegaMinor = new Planet("Vega Minor", 2);
        Planet vegaMajor = new Planet("Vega Major", 5);
        Planet industrex = new Planet("Industrex", 6);
        Planet rigelOne = new Planet("Rigel I", 2);
        Planet rigelTwo = new Planet("Rigel II", 4);
        Planet mirage = new Planet("Mirage", 3);

        //create PlanetLists with preset definitions
        PlanetList centerPlanets = new PlanetListBuilder().addPlanet(mecatolRex).build();
        PlanetList northPlanets = new PlanetListBuilder().addPlanet(vegaMinor).addPlanet(vegaMajor).build();
        PlanetList northEastPlanets = new PlanetListBuilder().build();
        PlanetList southEastPlanets = new PlanetListBuilder().addPlanet(industrex).build();
        PlanetList southPlanets = new PlanetListBuilder().addPlanet(rigelOne).addPlanet(rigelTwo).build();
        PlanetList southWestPlanets = new PlanetListBuilder().build();
        PlanetList northWestPlanets = new PlanetListBuilder().addPlanet(mirage).build();

        //create SystemTiles with preset definitions
        SystemTile centerSystem = new SystemTile(SystemPosition.C, unitList01, centerPlanets);
        SystemTile northSystem = new SystemTile(SystemPosition.N, unitList02, northPlanets);
        SystemTile northEastSystem = new SystemTile(SystemPosition.NE);
        SystemTile southEastSystem = new SystemTile(SystemPosition.SE, southEastPlanets);
        SystemTile southSystem = new SystemTile(SystemPosition.S, southPlanets);
        SystemTile southWestSystem = new SystemTile(SystemPosition.SW);
        SystemTile northWestSystem = new SystemTile(SystemPosition.NW, northWestPlanets);

        //create SystemTileList with builder and add all SystemTiles
        SystemTileList systemTileList = new SystemTileListBuilder().addSystemTile(centerSystem).addSystemTile(northSystem)
                .addSystemTile(northEastSystem).addSystemTile(southEastSystem).addSystemTile(southSystem)
                .addSystemTile(southWestSystem).addSystemTile(northWestSystem).build();

        return new Galaxy(systemTileList);
    }
}
