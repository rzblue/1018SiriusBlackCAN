package org.usfirst.frc.team1018.lib.util;

/**
 * @author Ryan Blue
 */
public class Utils {
    public static double limit(double value, double limit) {
        double absVal = Math.abs(value);
        return absVal > limit ? limit * (value < 0 ? -1 : 1) : value;
    }

}
