package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.*;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTile;

class TwilightImperium {

    public static void main(String[] args) {

        GalaxyCreator galaxyCreator = new GalaxyCreator();
        Galaxy presetGalaxy = galaxyCreator.createPresetGame();
        System.out.println("check cardinal congruency " + presetGalaxy.checkGalaxyLegality());

        System.out.println(SystemPosition.C.toString());

    }
}
