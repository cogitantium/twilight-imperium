package dk.aau.cs.fvejlb17.twilight.units.comparators;

import dk.aau.cs.fvejlb17.twilight.units.Ships;

import java.util.Comparator;

public class UnitCombatResourceComparator implements Comparator<Ships> {

    //compares firstly combatValue ascending then by resourceCost descending
    @Override
    public int compare(Ships ship1, Ships ship2) {

        //if ship1 is greater than ship2 in combatValue, return positive
        if (ship1.getCombatValue() > ship2.getCombatValue()) return 1;
            //if ship2 is greater than ship1 in combatValue, return negative
        else if (ship1.getCombatValue() < ship2.getCombatValue()) return -1;

        //if both ships combatValue and resourceCost is equal, return 0 implying no further sorting
        if (ship1.getResourceCost() == ship2.getResourceCost()) return 0;

        //if neither ship has a greater combat value than the other, return (ship2-ship1) difference in resource cost
        return ship2.getResourceCost() - ship1.getResourceCost();

    }
}
