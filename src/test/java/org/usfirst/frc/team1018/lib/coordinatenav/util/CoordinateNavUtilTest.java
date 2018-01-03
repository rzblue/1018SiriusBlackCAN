package org.usfirst.frc.team1018.lib.coordinatenav.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Ryan Blue
 */
public class CoordinateNavUtilTest {
    @Test
    public void testSquare() {
        double result = CoordinateNavUtil.square(4.5);
        double expectedResult = 20.25;
        assertEquals(result, expectedResult, 0);
    }
}
