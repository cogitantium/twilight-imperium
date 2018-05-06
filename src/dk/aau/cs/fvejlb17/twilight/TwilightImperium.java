package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.*;

public class TwilightImperium {

    public static void main(String[] args) {

        GalaxyCreator galaxyCreator = new GalaxyCreator();
        Galaxy presetGalaxy = galaxyCreator.createPresetGame();
        System.out.println("check cardinal congruency " + presetGalaxy.checkGalaxyLegality());


    }
}
