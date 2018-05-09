package dk.aau.cs.fvejlb17.twilight.units;

public class UnitListBuilder {

    private final UnitList unitList = new UnitList();

    public UnitListBuilder addUnit(Ships ship) {
        this.unitList.add(ship);
        return this;
    }

    public UnitList build() {
        return unitList;
    }
}
