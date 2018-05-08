package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.Galaxy;
import dk.aau.cs.fvejlb17.twilight.galaxies.GalaxyCreator;

class TwilightImperium {

    public static void main(String[] args) {

        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();

        Galaxy randomGalaxy = new GalaxyCreator().createRandomGame(6);
    }
}
