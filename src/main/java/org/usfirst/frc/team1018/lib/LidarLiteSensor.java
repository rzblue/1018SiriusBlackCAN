package org.usfirst.frc.team1018.lib;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

import java.util.TimerTask;

/**
 * Driver for a Lidar Lite sensor
 * //TODO: GET THIS DANG THING WORKING (apparently 254 can't get it to work either...)
 */
public class LidarLiteSensor {
    private final static int LIDAR_ADDR = 0x62;
    private final static int LIDAR_CONFIG_REGISTER = 0x00;
    private final static int LIDAR_DISTANCE_REGISTER = 0x8f;
    private I2C i2C;
    private byte[] distance;
    private java.util.Timer updater;
    private boolean hasSignal;

    public LidarLiteSensor(Port port) {
        i2C = new I2C(port, LIDAR_ADDR);
        distance = new byte[2];
        updater = new java.util.Timer();
        hasSignal = false;
    }

    /**
     * @return Distance in inches
     */
    public double getDistance() {
        int distCm = (int) Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
        return distCm / 2.54;
    }

    /**
     * @return true iff the sensor successfully provided data last loop
     */
    public boolean hasSignal() {
        return hasSignal;
    }

    /**
     * Start 10Hz polling
     */
    public void start() {
        start(100);
    }

    /**
     * Start polling for period in milliseconds
     */
    public void start(int period) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        updater.scheduleAtFixedRate(task, 0, period);
    }

    public void stop() {
        updater.cancel();
        updater = new java.util.Timer();
    }

    private void update() {
        if(i2C.write(LIDAR_CONFIG_REGISTER, 0x04)) {
            // the write failed to ack
            hasSignal = false;
            return;
        }
        Timer.delay(0.04); // Delay for measurement to be taken
        if(!i2C.read(LIDAR_DISTANCE_REGISTER, 2, distance)) {
            // the read failed
            hasSignal = false;
            return;
        }
        hasSignal = true;
        Timer.delay(0.005); // Delay to prevent over polling
    }
}
