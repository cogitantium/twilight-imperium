package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import dk.aau.cs.fvejlb17.twilight.systems.SystemPosition;

interface Units {

    Player getOwner();

    int getResourceCost();

    int getCombatValue();

    int getMovementSpeed();

    int getCapacity();

    SystemPosition getSystemPosition();

    void setSystemPosition(SystemPosition systemPosition);
}
