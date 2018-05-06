package dk.aau.cs.fvejlb17.twilight.galaxies;

class GalaxyContainsSystemWithPlanetNumExceedingLimitException extends Exception {

    GalaxyContainsSystemWithPlanetNumExceedingLimitException(int exeedingNum) {
        super("ERROR: A SystemTile in Galaxy contains more planets than: " + exeedingNum);
    }
}
