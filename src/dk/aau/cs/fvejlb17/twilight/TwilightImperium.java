package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.*;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;

class TwilightImperium {

    public static void main(String[] args) {

        GalaxyCreator galaxyCreator = new GalaxyCreator();
        Galaxy presetGalaxy = galaxyCreator.createPresetGame();
        System.out.println("check cardinal congruency " + presetGalaxy.checkGalaxyLegality());

        System.out.println(SystemPosition.C.toString());
        Player playerCrassus = new Player("Crassus", "The Emirates of Hacan", "Blue");
        System.out.println(presetGalaxy.getAllShipsInGalaxyByOwnerSorted(playerCrassus));

    }
}
