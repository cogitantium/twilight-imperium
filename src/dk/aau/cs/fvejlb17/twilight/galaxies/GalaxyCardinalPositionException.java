package dk.aau.cs.fvejlb17.twilight.galaxies;

class GalaxyCardinalPositionException extends Exception {

    GalaxyCardinalPositionException() {
        super("ERROR: Cardinal positions are not congruent!");
    }

    GalaxyCardinalPositionException(String message) {
        super("ERROR: Cardinal positions are not congruent! " + message);
    }
}
