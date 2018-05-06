package dk.aau.cs.fvejlb17.twilight.galaxies;

class GalaxyCardinalPositionException extends Exception {

    GalaxyCardinalPositionException() {
        super("ERROR: Cardinal positions are not congruent!");
    }

    GalaxyCardinalPositionException(String position) {
        super("ERROR: Cardinal positions at " + position + " are not congruent!");
    }

    GalaxyCardinalPositionException(String level, String message) {
        super(level + ": Cardinal positions are not congruent! " + message);
    }
}
