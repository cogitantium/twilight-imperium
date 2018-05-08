package dk.aau.cs.fvejlb17.twilight.utilities;

import java.util.Random;

import static java.time.Instant.now;

public class Maths {

    //returns random integer between lower and upper, seeded with unix timestamp
    public int randomIntBetweenBounds(int upper, int lower) {
        return new Random(now().getEpochSecond()).nextInt(upper-lower) + lower;
    }
}
