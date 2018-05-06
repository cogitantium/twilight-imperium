package dk.aau.cs.fvejlb17.twilight.systems;

import java.util.Arrays;

public class SystemTilePositionListBuilder {

    private final SystemTilePositionList systemTilePositionList = new SystemTilePositionList();

    public SystemTilePositionListBuilder addNeighbour(SystemPosition systemPosition) {
        try {
            this.systemTilePositionList.add(systemPosition);
        } catch (Exception e) {
            System.out.println("ERROR: trying to add SystemPosition to SystemTilePositionList: "
                    + e.getMessage());
        }
        return this;
    }

    //improve simplicity of constructor by method to add all SystemTile.SystemPosition-values
    public SystemTilePositionListBuilder addAllNeighbours() {
        try {
            this.systemTilePositionList.addAll(Arrays.asList(SystemPosition.values()));
        } catch (Exception e) {
            System.out.println("ERROR: trying to add SystemPosition to SystemTilePositionList: "
                    + e.getMessage());
        }
        return this;
    }

    public SystemTilePositionList build() {
        return systemTilePositionList;
    }
}
