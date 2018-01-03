package org.usfirst.frc.team1018.lib.vision;

/**
 * @author Ryan Blue
 */
public abstract class VisionClient {

    public abstract void putStatus(boolean status);

    public abstract boolean getStatus();

    public abstract void putFps(double fps);

    public abstract double getFps();

    public abstract void putTargetFound(boolean targetFound);

    public abstract boolean getTargetFound();

    public abstract void putNumberOfTargets(int numberOfTargets);

    public abstract int getNumberOfTargets();

    public abstract void putTargetAimingCenterX(double aimingCenterX);

    public abstract double getTargetAimingCenterX();

    public abstract void putXOffsetToTarget(double xOffsetToTarget);

    public abstract double getXOffsetToTarget();

    public abstract void putZOffsetToTarget(double zOffsetToTarget);

    public abstract double getZOffsetToTarget();

    public abstract void putYAngleToTarget(double yAngleToTarget);

    public abstract double getYAngleToTarget();

    public void putDataPacket(VisionPacket packet) {
        putFps(packet.fps);
        putTargetFound(packet.targetFound);
        putNumberOfTargets(packet.numberOfTargets);
        putTargetAimingCenterX(packet.aimingCenterX);
        putXOffsetToTarget(packet.xOffset);
        putZOffsetToTarget(packet.zOffset);
        putYAngleToTarget(packet.yAngleDegrees);
    }

    public VisionPacket getLatestDataPacket() {
        return new VisionPacket(
                getFps(),
                getTargetFound(),
                getNumberOfTargets(),
                getTargetAimingCenterX(),
                getXOffsetToTarget(),
                getZOffsetToTarget(),
                getYAngleToTarget()
        );
    }

    public abstract boolean isConnected();
}
