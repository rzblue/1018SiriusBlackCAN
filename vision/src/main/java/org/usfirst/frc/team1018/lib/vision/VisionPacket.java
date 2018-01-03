package org.usfirst.frc.team1018.lib.vision;

/**
 * @author Ryan Blue
 */
public class VisionPacket {

    public double fps;
    public boolean targetFound;
    public int numberOfTargets;
    public double aimingCenterX;
    public double xOffset;
    public double zOffset;
    public double yAngleDegrees;

    public VisionPacket(double fps, boolean targetFound, int numberOfTargets,
                        double aimingCenterX, double xOffset,
                        double zOffset, double yAngleDegrees) {
        this.fps = fps;
        this.targetFound = targetFound;
        this.numberOfTargets = numberOfTargets;
        this.aimingCenterX = aimingCenterX;
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.yAngleDegrees = yAngleDegrees;
    }

    public VisionPacket() {
        this(0, false, 0, 0, 0, 0, 0);
    }
}
