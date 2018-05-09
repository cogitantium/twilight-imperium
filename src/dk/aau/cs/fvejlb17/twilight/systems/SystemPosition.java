package dk.aau.cs.fvejlb17.twilight.systems;

public enum SystemPosition {
    C, N, NE, SE, S, SW, NW;

    //included for more verbose createPlanetaryControlFile output
    @Override
    public String toString() {
        return "SystemPosition" + this.name();
    }
}