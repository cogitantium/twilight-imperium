package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;

public interface Units {

    Player getOwner();
    int getResourceCost();
    int getCombatValue();
    int getMovementSpeed();
    int getCapacity();
}
