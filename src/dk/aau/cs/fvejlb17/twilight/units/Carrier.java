package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;

public class Carrier extends Ships {

    public Carrier(Player owner) {
        //hardcoding values for according to documentation
        super(owner, 3, 9, 1, 6);
    }

}
