package dk.aau.cs.fvejlb17.twilight.systems;

public class SystemTileListBuilder {

    private SystemTileList systemTileList = new SystemTileList();

    public SystemTileListBuilder addSystemTile(SystemTile systemTile) {
        try {
            this.systemTileList.add(systemTile);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return this;
    }

    public SystemTileList build() {
        return systemTileList;
    }
}
