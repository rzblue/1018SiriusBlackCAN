package org.usfirst.frc.team1018.lib.coordinatenav;

/**
 * @author Ryan Blue
 */
public class CoordinateDef {
    public final double angle;
    public final double encoder;
    public final boolean fuelOut;
    public final boolean pegDriver;
    public final boolean encoderReset;
    public final double maxDriveSpeed;
    public final double waitTime;

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
