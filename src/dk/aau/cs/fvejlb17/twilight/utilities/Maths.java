package dk.aau.cs.fvejlb17.twilight.utilities;

import java.util.Random;

import static java.time.Instant.now;

public class Maths {

    //returns random integer between lower and upper, seeded with unix timestamp
    public static int randomBetween(int lower, int upper) {
        return new Random(now().getNano()).nextInt(upper - lower) + lower;
    }
}
