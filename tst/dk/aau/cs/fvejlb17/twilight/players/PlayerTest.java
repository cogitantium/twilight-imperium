package dk.aau.cs.fvejlb17.twilight.players;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void toStringTest() {
        Player player01 = new Player("Player01", "Race01", "Red");
        assertEquals("Player{name='Player01', race='Race01', colour='Red'}", player01.toString());
    }

    @Test
    void equalsTest() {
        Player player01 = new Player("Player01", "Race01", "Red");
        Player player02 = new Player("Player01", "Race01", "Red");
        Player player03 = new Player("Player02", "Race01", "Red");
        assertTrue(player01.equals(player02));
        assertTrue(player02.equals(player01));
        assertFalse(player01.equals(player03));
    }

    @Test
    void hashCodeTest() {
        int hash = Objects.hash("Player01", "Race01", "Red");
        Player player01 = new Player("Player01", "Race01", "Red");
        Player player02 = new Player("Player01", "Race01", "Red");
        assertEquals(player01.hashCode(), player02.hashCode());
        assertEquals(hash, player01.hashCode());

    }
}