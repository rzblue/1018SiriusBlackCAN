package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;

/**
 * @author Ryan Blue
 * RobotConfig is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * <p>
 * No actuator objects should be declared in this class, only port numbers and configuration related to each.
 */
public final class RobotConfig {
    private RobotConfig() {}

    public static final class DrivetrainConfig {
        //Actuators
        public static final int MOTOR_REAR_RIGHT_PWM = 0;
        public static final int MOTOR_REAR_LEFT_PWM = 1;
        public static final int MOTOR_FRONT_RIGHT_PWM = 2;
        public static final int MOTOR_FRONT_LEFT_PWM = 3;

        //Sensors
        public static final int ENCODER_RIGHT_A_DIO = 4;
        public static final int ENCODER_RIGHT_B_DIO = 5;
        public static final int ENCODER_LEFT_A_DIO = 6;
        public static final int ENCODER_LEFT_B_DIO = 7;

        public static final SPI.Port NAVX_SPI = SPI.Port.kMXP;

        //Configuration options
        public static final boolean FIELD_ORIENTED_CFG = false;

        public static final boolean RIGHT_ENCODER_REVERSE_CFG = true;
        public static final boolean LEFT_ENCODER_REVERSE_CFG = false;
        public static final double ENCODER_DIST_PER_PULSE_CFG = 0.074;

        private DrivetrainConfig() {}
    }

    public static final class GearRotatorConfig {
        public static final int GEAR_ROTATOR_PWM = 4;

        public static final int BANNER_DIO = 8;

        public static final I2C.Port LIDAR_I2C = I2C.Port.kMXP;

        private GearRotatorConfig() {}
    }

    public static final class ClimberConfig {
        public static final int CLIMBER_LOWER_PWM = 5;
        public static final int CLIMBER_UPPER_PWM = 6;

        private ClimberConfig() {}
    }

    public static class PaddlesConfig {
        public static final int PADDLES_REV_SOL = 3;
        public static final int PADDLES_FOR_SOL = 2;

        private PaddlesConfig() {}
    }

    public static class BrakesConfig {
        public static final int BRAKES_REV_SOL = 0;
        public static final int BRAKES_FOR_SOL = 1;

        private BrakesConfig() {}
    }
}
