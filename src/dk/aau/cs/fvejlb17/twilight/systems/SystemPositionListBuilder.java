package dk.aau.cs.fvejlb17.twilight.systems;

import java.util.Arrays;

public class SystemPositionListBuilder {

    private final SystemPositionList systemPositionList = new SystemPositionList();

    public SystemPositionListBuilder addNeighbour(SystemPosition systemPosition) {
        this.systemPositionList.add(systemPosition);
        return this;
    }

    //improve simplicity of constructor by method to add all SystemPosition values
    public SystemPositionListBuilder addAllNeighbours() {
        this.systemPositionList.addAll(Arrays.asList(SystemPosition.values()));
        return this;
    }

    public SystemPositionList build() {
        return systemPositionList;
    }
}
