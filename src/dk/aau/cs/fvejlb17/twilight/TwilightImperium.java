package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.*;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;

class TwilightImperium {

    public static void main(String[] args) {

        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();
        System.out.println("check cardinal congruency " + presetGalaxy.checkGalaxyLegality());

        System.out.println(SystemPosition.C.toString());
        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        //System.out.println(presetGalaxy.getAllShipsInGalaxyByOwnerSorted(playerCrassus));

        System.out.println("Testing moveShip");

        UnitList unitList = presetGalaxy.getAllShipsInGalaxyByOwnerSorted(playerCrassus);
        System.out.println(unitList.get(0));
        SystemTileList systemTileList = presetGalaxy.getAllSystemsInGalaxy();

        System.out.println(systemTileList.moveShip(unitList.get(0), systemTileList.get(0), systemTileList.get(1)));
        System.out.println(systemTileList.moveShip(unitList.get(0), systemTileList.get(1), systemTileList.get(5)));
    }
}
