package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipsTest {

    @Test
    void getOwner() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Dreadnought dreadnought01 = new Dreadnought(player01);
        assertEquals(player01, dreadnought01.getOwner());
    }

    @Test
    void getResourceCost() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Dreadnought dreadnought01 = new Dreadnought(player01);
        assertEquals(5, dreadnought01.getResourceCost());
    }

    @Test
    void getCombatValue() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Dreadnought dreadnought01 = new Dreadnought(player01);
        assertEquals(5, dreadnought01.getCombatValue());
    }

    @Test
    void getMovementSpeed() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Dreadnought dreadnought01 = new Dreadnought(player01);
        assertEquals(1, dreadnought01.getMovementSpeed());

    }

    @Test
    void getCapacity() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Dreadnought dreadnought01 = new Dreadnought(player01);
        Carrier carrier01 = new Carrier(player01);
        assertEquals(0, dreadnought01.getCapacity());
        assertEquals(6, carrier01.getCapacity());

    }
}