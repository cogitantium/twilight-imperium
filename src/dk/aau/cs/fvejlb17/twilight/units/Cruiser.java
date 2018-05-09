package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;

public class Cruiser extends Ships {

    public Cruiser(Player owner) {
        //hardcoding values for according to documentation
        super(owner, 2, 7, 2, 0);
    }
}
