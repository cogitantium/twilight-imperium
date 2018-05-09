package dk.aau.cs.fvejlb17.twilight.units.comparators;

import dk.aau.cs.fvejlb17.twilight.units.Ships;

import java.util.Comparator;

public class UnitResourceComparator implements Comparator<Ships> {

    //compares resourceCost ascending
    @Override
    public int compare(Ships ship1, Ships ship2) {

        //if resourceCost is equal, return 0 as sorting is finished
        if (ship1.getResourceCost() == ship2.getResourceCost()) return 0;

        //if ship1 is greater than ship2 in resourceCost, return negative, else positive
        if (ship1.getResourceCost() > ship2.getResourceCost()) return -1;
        else return 1;
    }
}
