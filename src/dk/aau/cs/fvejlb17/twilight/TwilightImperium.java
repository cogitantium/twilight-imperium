package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.galaxies.*;
import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;
import dk.aau.cs.fvejlb17.twilight.systems.SystemTileList;
import dk.aau.cs.fvejlb17.twilight.units.UnitList;
import dk.aau.cs.fvejlb17.twilight.utilities.WriteGameStateToFile;

import java.io.IOException;

class TwilightImperium {

    public static void main(String[] args) {

        Galaxy presetGalaxy = new GalaxyCreator().createPresetGame();
    }
}
