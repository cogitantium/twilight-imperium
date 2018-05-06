package dk.aau.cs.fvejlb17.twilight.systems;

//abstracting postions by enumerating from 0 to 6
public enum SystemPosition {
    C, N, NE, SE, S, SW, NW;

    @Override
    public String toString() {
        return "SystemPosition" + this.name();
    }
}