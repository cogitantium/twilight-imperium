package dk.aau.cs.fvejlb17.twilight;

import dk.aau.cs.fvejlb17.twilight.units.Dreadnought;

public class TwilightImperium {

    public static void main(String[] args) {

        Player player01 = new Player("Player01", "Race01", "Red");
        Player player02 = new Player("Player02", "Race02", "Blue");
        System.out.println(player01.toString());
        System.out.println(player02.toString());

        Dreadnought dreadnought01 = new Dreadnought(player01);
        System.out.println(dreadnought01.getCombatValue());
    }
}
