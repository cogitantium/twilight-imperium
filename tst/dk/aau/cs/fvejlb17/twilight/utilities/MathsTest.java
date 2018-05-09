package dk.aau.cs.fvejlb17.twilight.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathsTest {

    @Test
    void randomIntBetweenBoundsTest1() {
        //assert that, between two identical runs, that randomBetween is actually between bounds
        assertTrue((10 <= Maths.randomBetween(10, 20) && Maths.randomBetween(10, 20) <= 20));
    }

    @Test
    void randomIntBetweenBoundsTest2() {
        //assert false that randomBetween is out of bounds from both bounds
        assertFalse(10 > Maths.randomBetween(10, 20));
        assertFalse(Maths.randomBetween(10, 20) > 20);
    }

    @Test
    void randomIntBetweenBoundsTest3() {
        //assert that returned integer is inclusive of bounds, specifically if it's either bound
        int intTest = Maths.randomBetween(5, 6);
        assertTrue(intTest == 5 ^ intTest == 6);
    }
}