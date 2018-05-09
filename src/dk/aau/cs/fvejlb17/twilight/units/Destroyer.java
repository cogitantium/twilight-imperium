package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;

public class Destroyer extends Ships {

    public Destroyer(Player owner) {
        //hardcoding values for according to documentation
        super(owner, 1, 9, 2, 0);
    }
}
