package dk.aau.cs.fvejlb17.twilight.systems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemTileListBuilderTest {

    @Test
    void addSystemTile() {
        SystemTile systemTile01 = new SystemTile(SystemPosition.C);
        SystemTile systemTile02 = new SystemTile(SystemPosition.N);

        SystemTileList systemTileList01 = new SystemTileList();

        systemTileList01.add(systemTile01);
        systemTileList01.add(systemTile02);

        SystemTileList systemTileList02 = new SystemTileListBuilder()
                .addSystemTile(systemTile01).addSystemTile(systemTile02).build();

        assertEquals(systemTileList01.get(0), systemTileList02.get(0));
        assertEquals(systemTileList01.get(1), systemTileList02.get(1));

    }

    @Test
    void build() {
        SystemTile systemTile01 = new SystemTile(SystemPosition.C);
        SystemTile systemTile02 = new SystemTile(SystemPosition.N);

        SystemTileList systemTileList01 = new SystemTileList();

        systemTileList01.add(systemTile01);
        systemTileList01.add(systemTile02);

        SystemTileList systemTileList02 = new SystemTileListBuilder()
                .addSystemTile(systemTile01).addSystemTile(systemTile02).build();

        assertEquals(systemTileList01.getClass(), systemTileList02.getClass());
    }
}