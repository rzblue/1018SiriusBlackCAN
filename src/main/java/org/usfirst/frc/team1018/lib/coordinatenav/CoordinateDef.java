package org.usfirst.frc.team1018.lib.coordinatenav;

/**
 * @author Ryan Blue
 */
public class CoordinateDef {
    public double angle;
    public double encoder;
    public boolean fuelOut;
    public boolean pegDriver;
    public boolean encoderReset;
    public double maxDriveSpeed;
    public double waitTime;

    public CoordinateDef(double angle, double encoder, boolean fuelOut, boolean pegDriver, boolean encoderReset, double maxDriveSpeed, double waitTime) {
        this.angle = angle;
        this.encoder = encoder;
        this.fuelOut = fuelOut;
        this.pegDriver = pegDriver;
        this.encoderReset = encoderReset;
        this.maxDriveSpeed = maxDriveSpeed;
        this.waitTime = waitTime;
    }
}
