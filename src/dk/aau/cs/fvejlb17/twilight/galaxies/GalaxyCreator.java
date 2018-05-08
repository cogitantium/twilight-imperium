package dk.aau.cs.fvejlb17.twilight.galaxies;

import dk.aau.cs.fvejlb17.twilight.planets.Planet;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetList;
import dk.aau.cs.fvejlb17.twilight.planets.PlanetListBuilder;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.players.PlayerList;
import dk.aau.cs.fvejlb17.twilight.players.PlayerListBuilder;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileListBuilder;
import dk.aau.cs.fvejlb17.twilight.units.*;
import dk.aau.cs.fvejlb17.twilight.utilities.FileReader;
import dk.aau.cs.fvejlb17.twilight.utilities.Maths;

import java.util.ArrayList;
import java.util.List;

public class GalaxyCreator {

    public Galaxy createPresetGame() {

        //create players and PlayerList from preset definitions
        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player playerPompey = new Player("Pompey", "Federation of Sol", "Red");
        PlayerList playerList = new PlayerListBuilder().addPlayer(playerCrassus).addPlayer(playerPompey).build();

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
        PlanetList southEastPlanets = new PlanetListBuilder().addPlanet(industrex).build();
        PlanetList southPlanets = new PlanetListBuilder().addPlanet(rigelOne).addPlanet(rigelTwo).build();
        PlanetList northWestPlanets = new PlanetListBuilder().addPlanet(mirage).build();

        //create SystemTiles with preset definitions and containing ships
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

        return new Galaxy(systemTileList, playerList);
    }

    public Galaxy createRandomGame(int maxNumShipsPerPlayer) {

        //creating random numPlayers above 2, for sensible game and below 6 for sufficient names to name players
        int numPlayers = Maths.randomBetween(2, 6);

        //get String array of playerNames, playerRaces, playerColours and planetNames
        List<String> playerNames = FileReader.getLinesFromFile("names");
        List<String> playerRaces = FileReader.getLinesFromFile("races");
        List<String> playerColours = FileReader.getLinesFromFile("colours");
        List<String> planetNames = FileReader.getLinesFromFile("planets");

        //TODO should avoid duplicates
        //create PlayerList to hold created players
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < numPlayers; i++) {
            //for numPlayers, add new Player to PlayerList with random attributes between 0 and playerNames.size
            playerList.add(new Player(playerNames.get(Maths.randomBetween(0, playerNames.size())),
                    playerRaces.get(Maths.randomBetween(0, playerNames.size())),
                    playerColours.get(Maths.randomBetween(0, playerNames.size()))));
        }

        //create random number of ships per player of at least two ships,
        // but not above maxNumShipsPerPlayer to make random game fair
        int numShipsPerPlayer = Maths.randomBetween(2, maxNumShipsPerPlayer);

        //create a list of UnitLists for aliasing players ships to a specific UnitList
        UnitListList unitListList = new UnitListList();

        //for numPlayers, do
        for (int i = 0; i < numPlayers; i++) {

            //choose one type of ship for each player - this would optimally be dynamically calculated at runtime and
            //could have had further rules, eg. that if a Carrier exists in UnitList, an offensive ship must be added
            switch (Maths.randomBetween(0, 3)) {
                case 0:
                    UnitListBuilder unitListBuilder0 = new UnitListBuilder();
                    for (int j = 0; j < numShipsPerPlayer; j++) {
                        //create a destroyer with owner equal to current Player iterating through outer loop
                        unitListBuilder0 = new UnitListBuilder().addUnit(new Destroyer(playerList.get(i)));
                    }
                    //finally adds built UnitList to unitListList at same index as corresponding player in PlayerList
                    unitListList.add(unitListBuilder0.build());
                    break;
                case 1:
                    UnitListBuilder unitListBuilder1 = new UnitListBuilder();
                    for (int j = 0; j < numShipsPerPlayer; j++) {
                        //create a cruiser with owner equal to current Player iterating through outer loop
                        unitListBuilder1 = new UnitListBuilder().addUnit(new Cruiser(playerList.get(i)));
                    }
                    //finally adds built UnitList to unitListList at same index as corresponding player in PlayerList
                    unitListList.add(unitListBuilder1.build());
                    break;
                case 2:
                    UnitListBuilder unitListBuilder2 = new UnitListBuilder();
                    for (int j = 0; j < numShipsPerPlayer; j++) {
                        //create a carrier with owner equal to current Player iterating through outer loop
                        unitListBuilder2 = new UnitListBuilder().addUnit(new Carrier(playerList.get(i)));
                    }
                    //finally adds built UnitList to unitListList at same index as corresponding player in PlayerList
                    unitListList.add(unitListBuilder2.build());
                    break;
                case 3:
                    UnitListBuilder unitListBuilder3 = new UnitListBuilder();
                    for (int j = 0; j < numShipsPerPlayer; j++) {
                        //create a dreadnought with owner equal to current Player iterating through outer loop
                        unitListBuilder3 = new UnitListBuilder().addUnit(new Dreadnought(playerList.get(i)));
                    }
                    //finally adds built UnitList to unitListList at same index as corresponding player in PlayerList
                    unitListList.add(unitListBuilder3.build());
                    break;
            }
        }

        //create SystemTileList to collect all SystemTiles created below
        SystemTileList systemTileList = new SystemTileList();


        //for all SystemTiles but centerSystem, do
        for (int i = 0; i < 6; i++) {
            PlanetListBuilder planetListBuilder = new PlanetListBuilder();

            //randomly between 0 and 3, create planets
            for (int j = 0; j < Maths.randomBetween(0, 3); j++) {

                //pick a name from planetNames and store index in variable
                int index = Maths.randomBetween(0, planetNames.size());
                //create new Planet with chosen planetName and random resource production
                planetListBuilder.addPlanet(new Planet(planetNames.get(index), Maths.randomBetween(0, 6)));
                //remove chosen planetName from planetNames to avoid duplicate names
                planetNames.remove(index);
            }

            //if a players units are yet to be assigned a SystemTile,
            // create SystemTile with first UnitList and remove UnitList from unitListList
            if (!unitListList.isEmpty()) {
                systemTileList.add(new SystemTile(unitListList.get(0), planetListBuilder.build()));
                unitListList.remove(0);
            } else {
                //create SystemTile with generated planets and add it to systemTileList if no UnitLists remain
                systemTileList.add(new SystemTile(planetListBuilder.build()));
            }

        }

        //manually create array of SystemPositions for randomly assigning position to generated SystemTiles
        //this case of hardcoding values is not optimal and would be avoided with more time
        ArrayList<SystemPosition> systemPositionsArray = new ArrayList<>();
        systemPositionsArray.add(SystemPosition.N);
        systemPositionsArray.add(SystemPosition.NE);
        systemPositionsArray.add(SystemPosition.SE);
        systemPositionsArray.add(SystemPosition.S);
        systemPositionsArray.add(SystemPosition.SW);
        systemPositionsArray.add(SystemPosition.NW);

        //assign random SystemPosition to each SystemTile in systemTileList, letting setSystemPosition method
        //correctly calculate neighbour SystemPositions
        for (SystemTile systemTile : systemTileList) {
            //choose random SystemPosition between 0 and remaining SystemPositions in systemPositionArray
            int index = Maths.randomBetween(0, systemPositionsArray.size());
            //set SystemPosition of current SystemTile
            systemTile.setSystemPosition(systemPositionsArray.get(index));
            //remove SystemPosition chosen for systemTile, to avoid duplicates
            systemPositionsArray.remove(index);
        }

        //create centerPlanet and SystemTile manually, as it must be Mecatol Rex, and add to systemTileList
        Planet centerPlanet = new Planet("Mecatol Rex", Maths.randomBetween(0, 6));
        PlanetList planetList = new PlanetListBuilder().addPlanet(centerPlanet).build();
        SystemTile centerSystem = new SystemTile(SystemPosition.C, planetList);
        systemTileList.add(centerSystem);

        return new Galaxy(systemTileList, playerList);
    }
}
