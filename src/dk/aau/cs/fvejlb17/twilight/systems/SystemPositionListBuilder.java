package dk.aau.cs.fvejlb17.twilight.systems;

import java.util.Arrays;

public class SystemPositionListBuilder {

    private final SystemPositionList systemPositionList = new SystemPositionList();

    public SystemPositionListBuilder addNeighbour(SystemPosition systemPosition) {
        try {
            this.systemPositionList.add(systemPosition);
        } catch (Exception e) {
            System.out.println("ERROR: trying to add SystemPosition to SystemPositionList: "
                    + e.getMessage());
        }
        return this;
    }

    //improve simplicity of constructor by method to add all SystemTile.SystemPosition-values
    public SystemPositionListBuilder addAllNeighbours() {
        try {
            this.systemPositionList.addAll(Arrays.asList(SystemPosition.values()));
        } catch (Exception e) {
            System.out.println("ERROR: trying to add SystemPosition to SystemPositionList: "
                    + e.getMessage());
        }
        return this;
    }

    public SystemPositionList build() {
        return systemPositionList;
    }
}
