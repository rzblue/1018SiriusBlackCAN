package org.usfirst.frc.team1018.lib;

import edu.wpi.first.wpilibj.I2C;

/**
 * @author Ryan Blue
 */
public class LidarLite extends I2C {
    private final static int LIDAR_ADDR = 0x62;
    private final static int LIDAR_START_REGISTER = 0x00;
    private final static int LIDAR_DISTANCE_REGISTER = 0x8f;
    private static byte[] distance;

    public LidarLite(I2C.Port port) {
        super(port, LIDAR_ADDR);
        resetRegister();
        write(0x45, 0x14);
        //setup aquisition mode
        write(0x04, 0x20);
        //setup multiple measurements
        write(0x11, 0xff);
        //Start measuring
        write(0x00, 0x04);
        distance = new byte[2];
    }

    public void resetRegister() {
        write(0x00, 0x00);
    }

    public double getDistanceInches() {
        return getDistanceCentimeters() / 2.54;
    }

    public int getDistanceCentimeters() {
        return (int) Integer.toUnsignedLong(getRawDistance()[0] << 8) + Byte.toUnsignedInt(getRawDistance()[1]);
    }

    public byte[] getRawDistance() {
        read(LIDAR_DISTANCE_REGISTER, 2, distance);
        return distance;
    }

    @Override
    public void free() {
        resetRegister();
        super.free();
    }

}
