package org.usfirst.frc.team1018.lib.coordinatenav.util;

/**
 * @author Ryan Blue
 */
public class Point2D {
    public static double x;
    public static double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D toVector() {
        return new Vector2D(Math.sqrt(CoordinateNavUtil.square(x) + CoordinateNavUtil.square(y)), Math.atan2(y, x));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
