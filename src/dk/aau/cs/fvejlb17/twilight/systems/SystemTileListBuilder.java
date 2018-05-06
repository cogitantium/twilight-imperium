package dk.aau.cs.fvejlb17.twilight.systems;

public class SystemTileListBuilder {

    private final SystemTileList systemTileList = new SystemTileList();

    public SystemTileListBuilder addSystemTile(SystemTile systemTile) {
        this.systemTileList.add(systemTile);
        return this;
    }

    public SystemTileList build() {
        return systemTileList;
    }
}
