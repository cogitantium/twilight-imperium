package dk.aau.cs.fvejlb17.twilight;

import org.junit.jupiter.api.Test;

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
        assertTrue(player01.equals(player02));
    }

    @Test
    void hashCodeTest() {
        Player player01 = new Player("Player01", "Race01", "Red");
        Player player02 = new Player("Player01", "Race01", "Red");
        assertEquals(player01.hashCode(), player02.hashCode());

    }
}