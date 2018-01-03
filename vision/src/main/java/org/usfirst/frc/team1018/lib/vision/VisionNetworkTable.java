package org.usfirst.frc.team1018.lib.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * @author Ryan Blue
 */
public class VisionNetworkTable extends VisionClient {

    private static final VisionNetworkTable instance = new VisionNetworkTable();
    private final NetworkTable visionTable;

    private VisionNetworkTable() {
        visionTable = NetworkTable.getTable(VisionTableKeys.VISION_TABLE_KEY);
        resetValues();
    }

    public static VisionNetworkTable getInstance() {
        return instance;
    }

    @Override
    public void putStatus(boolean running) {
        visionTable.putBoolean(VisionTableKeys.STATUS_KEY, running);
    }

    @Override
    public boolean getStatus() {
        return visionTable.getBoolean(VisionTableKeys.STATUS_KEY, false);
    }

    @Override
    public void putFps(double fps) {
        visionTable.putNumber(VisionTableKeys.FPS_KEY, fps);
    }

    @Override
    public double getFps() {
        return visionTable.getNumber(VisionTableKeys.FPS_KEY, 0);
    }

    @Override
    public void putTargetFound(boolean targetFound) {
        visionTable.putBoolean(VisionTableKeys.TARGET_FOUND_KEY, targetFound);
    }

    @Override
    public boolean getTargetFound() {
        return visionTable.getBoolean(VisionTableKeys.TARGET_FOUND_KEY, false);
    }

    @Override
    public void putNumberOfTargets(int numberOfTargets) {
        visionTable.putNumber(VisionTableKeys.NUMBER_OF_TARGETS_KEY, numberOfTargets);
    }

    @Override
    public int getNumberOfTargets() {
        return (int) visionTable.getNumber(VisionTableKeys.NUMBER_OF_TARGETS_KEY, 0);
    }

    @Override
    public void putTargetAimingCenterX(double centerX) {
        visionTable.putNumber(VisionTableKeys.CENTERX_AIMING_KEY, centerX);
    }

    @Override
    public double getTargetAimingCenterX() {
        return visionTable.getNumber(VisionTableKeys.CENTERX_AIMING_KEY, 0);
    }

    @Override
    public void putXOffsetToTarget(double xOffset) {
        visionTable.putNumber(VisionTableKeys.X_OFFSET_KEY, xOffset);
    }

    @Override
    public double getXOffsetToTarget() {
        return visionTable.getNumber(VisionTableKeys.X_OFFSET_KEY, 0);
    }

    @Override
    public void putZOffsetToTarget(double zOffset) {
        visionTable.putNumber(VisionTableKeys.Z_OFFSET_KEY, zOffset);
    }

    @Override
    public double getZOffsetToTarget() {
        return visionTable.getNumber(VisionTableKeys.Z_OFFSET_KEY, 0);
    }

    @Override
    public void putYAngleToTarget(double yAngle) {
        visionTable.putNumber(VisionTableKeys.Y_ANGLE_KEY, yAngle);
    }

    @Override
    public double getYAngleToTarget() {
        return visionTable.getNumber(VisionTableKeys.Y_ANGLE_KEY, 0);
    }

    public void resetValues() {

        putDataPacket(new VisionPacket(
                0,
                false,
                0,
                0,
                0,
                0,
                0)
        );

    }

    @Override
    public boolean isConnected() {
        return visionTable.isConnected();
    }

    public NetworkTable getVisionTable() {
        return visionTable;
    }

    private class VisionTableKeys {

        private static final String VISION_TABLE_KEY = "vision";

        private static final String STATUS_KEY = "status";
        private static final String FPS_KEY = "fps";
        private static final String TARGET_FOUND_KEY = "target_found";
        private static final String NUMBER_OF_TARGETS_KEY = "targets_found_amount";
        private static final String CENTERX_AIMING_KEY = "target_center_aiming_x";
        private static final String Z_OFFSET_KEY = "z_offset";
        private static final String X_OFFSET_KEY = "x_offset";
        private static final String Y_ANGLE_KEY = "y_angle";
    }

}
