package dk.aau.cs.fvejlb17.twilight.units;

import dk.aau.cs.fvejlb17.twilight.players.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnitListBuilderTest {

    @Test
    void addUnit() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Carrier carrier01 = new Carrier(player01);
        Carrier carrier02 = new Carrier(player01);
        UnitListBuilder unitListBuilder01 = new UnitListBuilder();
        UnitList unitList01 = unitListBuilder01.addUnit(carrier01).addUnit(carrier02).build();
        ArrayList<Ships> unitList02 = new ArrayList<>();
        unitList02.add(carrier01); unitList02.add(carrier02);

        assertEquals(carrier01, unitList01.get(0));
        assertEquals(carrier02, unitList01.get(1));
        assertEquals(unitList01, unitList02);
    }

    @Test
    void build() {
        Player player01 = new Player("playerName", "playerRace", "playerColor");
        Carrier carrier01 = new Carrier(player01);
        Carrier carrier02 = new Carrier(player01);
        UnitListBuilder unitListBuilder01 = new UnitListBuilder();
        UnitList unitList01 = unitListBuilder01.addUnit(carrier01).addUnit(carrier02).build();
        assertNotNull(unitList01);
    }
}