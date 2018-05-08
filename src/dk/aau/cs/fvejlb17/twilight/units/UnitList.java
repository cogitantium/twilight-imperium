package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.utilities.Maths;

import java.util.ArrayList;

public class UnitList extends ArrayList<Ships> {

    public int getNumHitsFromFleet() {
        int hits = 0;
        for (Ships ship : this) {
            //roll a d10, if value is greater or equal to current ship's combatValue, a hit has occurred
            if (Maths.randomBetween(1, 10) >= ship.getCombatValue()) hits++;
        }
        return hits;
    }
}
