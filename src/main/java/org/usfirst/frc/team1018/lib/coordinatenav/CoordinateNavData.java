package org.usfirst.frc.team1018.lib.coordinatenav;

/**
 * @author Ryan Blue
 */
public class CoordinateNavData {
    double xPos;
    double yPos;
    double angle;
    double magnitude;
    double currentAngleWaypoint;
    double currentEncoderWaypoing;
    double xOut;
    double yOut;

    public CoordinateNavData(double xPos, double yPos, double angle, double magnitude, double currentAngleWaypoint, double currentEncoderWaypoing, double xOut, double yOut) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        this.magnitude = magnitude;
        this.currentAngleWaypoint = currentAngleWaypoint;
        this.currentEncoderWaypoing = currentEncoderWaypoing;
        this.xOut = xOut;
        this.yOut = yOut;
    }
}
