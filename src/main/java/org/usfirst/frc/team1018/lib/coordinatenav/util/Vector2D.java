package org.usfirst.frc.team1018.lib.coordinatenav.util;

/**
 * @author Ryan Blue
 */
public class Vector2D {
    public final double magnitude;
    public final double theta;

    /**
     * Creates a new Vector2D with magnitude and theta
     *
     * @param magnitude magnitude of the vector
     * @param theta     angle in degrees of the vector
     */
    public Vector2D(double magnitude, double theta) {
        this.magnitude = magnitude;
        this.theta = theta;
    }

    public Point2D toPoint() {
        double angleRad = Math.toRadians(theta);
        return new Point2D(magnitude * Math.cos(angleRad), magnitude * Math.sin(angleRad));
    }


}
