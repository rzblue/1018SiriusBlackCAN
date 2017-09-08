package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team1018.robot.subsystems.GearRotator;

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

    public static final DrivetrainConfig DRIVETRAIN_CONFIG = new DrivetrainConfig();
    public static final GearRotatorConfig GEAR_ROTATOR_CONFIG = new GearRotatorConfig();
    public static final ClimberConfig CLIMBER_CONFIG = new ClimberConfig();
    public static final PaddlesConfig PADDLES_CONFIG = new PaddlesConfig();
    public static final BrakesConfig BRAKES_CONFIG = new BrakesConfig();

    public static final class DrivetrainConfig {
        //Actuators
        public final int MOTOR_REAR_RIGHT_PWM = 0;
        public final int MOTOR_REAR_LEFT_PWM = 1;
        public final int MOTOR_FRONT_RIGHT_PWM = 2;
        public final int MOTOR_FRONT_LEFT_PWM = 3;

        //Sensors
        public final int ENCODER_RIGHT_A_DIO = 4;
        public final int ENCODER_RIGHT_B_DIO = 5;
        public final int ENCODER_LEFT_A_DIO = 6;
        public final int ENCODER_LEFT_B_DIO = 7;

        public final SPI.Port NAVX_SPI = SPI.Port.kMXP;

        //Configuration options
        public final boolean FIELD_ORIENTED_CFG = false;

        public final boolean RIGHT_ENCODER_REVERSE_CFG = true;
        public final boolean LEFT_ENCODER_REVERSE_CFG = false;
        public final int ENCODER_TICKS_PER_REV_CFG = 256;
        public final double WHEEL_DIAMETER_M_CFG = 0.1524;
        public final double ENCODER_DIST_PER_PULSE_CFG = 0.074;


        public final double WHEELBASE_WIDTH_M_CFG = 0.635;

        public final Trajectory.Config TRAJECTORY_CFG = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, 0.05, 2.5, 2, 60);

        private DrivetrainConfig() {}
    }

    public static final class GearRotatorConfig {
        public final int GEAR_ROTATOR_PWM = 4;

        public final int BANNER_DIO = 8;

        public final I2C.Port LIDAR_I2C = I2C.Port.kMXP;

        private GearRotatorConfig() {}
    }

    public static final class ClimberConfig {
        public final int CLIMBER_LOWER_PWM = 5;
        public final int CLIMBER_UPPER_PWM = 6;

        private ClimberConfig() {}
    }

    public static class PaddlesConfig {
        public final int PADDLES_REV_SOL = 3;
        public final int PADDLES_FOR_SOL = 2;

        private PaddlesConfig() {}
    }

    public static class BrakesConfig {
        public final int BRAKES_REV_SOL = 0;
        public final int BRAKES_FOR_SOL = 1;

        private BrakesConfig() {}
    }
}
