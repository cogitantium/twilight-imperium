package dk.aau.cs.fvejlb17.twilight.units;

public class UnitListBuilder {
    private UnitList unitList = new UnitList();

    public UnitListBuilder addUnit(Ships ship) {
        try {
            this.unitList.add(ship);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return this;
    }

    public UnitList build() {
        return unitList;
    }
}
