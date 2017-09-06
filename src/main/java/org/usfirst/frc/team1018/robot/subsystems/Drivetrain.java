package org.usfirst.frc.team1018.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotConfig;

/**
 * @author Ryan Blue
 */
public class Drivetrain extends Subsystem {

    private static Drivetrain instance;

    private TalonSRX rearRight = new TalonSRX(RobotConfig.DrivetrainConfig.MOTOR_REAR_RIGHT_PWM);
    private TalonSRX rearLeft = new TalonSRX(RobotConfig.DrivetrainConfig.MOTOR_REAR_LEFT_PWM);
    private TalonSRX frontRight = new TalonSRX(RobotConfig.DrivetrainConfig.MOTOR_FRONT_RIGHT_PWM);
    private TalonSRX frontLeft = new TalonSRX(RobotConfig.DrivetrainConfig.MOTOR_FRONT_LEFT_PWM);

    private Encoder rightEncoder = new Encoder(RobotConfig.DrivetrainConfig.ENCODER_RIGHT_A_DIO, RobotConfig.DrivetrainConfig.ENCODER_RIGHT_B_DIO, RobotConfig.DrivetrainConfig.RIGHT_ENCODER_REVERSE_CFG);
    private Encoder leftEncoder = new Encoder(RobotConfig.DrivetrainConfig.ENCODER_LEFT_A_DIO, RobotConfig.DrivetrainConfig.ENCODER_LEFT_B_DIO, RobotConfig.DrivetrainConfig.LEFT_ENCODER_REVERSE_CFG);

    private AHRS navX = new AHRS(RobotConfig.DrivetrainConfig.NAVX_SPI);

    private RobotDrive driveHelper = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

    private boolean fieldOriented = RobotConfig.DrivetrainConfig.FIELD_ORIENTED_CFG;

    private Drivetrain() {
        rearRight.setInverted(true);
        frontRight.setInverted(true);
    }

    public static Drivetrain getInstance() {
        if(instance == null) instance = new Drivetrain();
        return instance;
    }

    public void mecanumDrive(double x, double y, double turn) {
        driveHelper.mecanumDrive_Cartesian(x, y, turn, fieldOriented ? navX.getYaw() : 0);
    }

    public void setLeftRightMotors(double left, double right) {
        rearLeft.set(left);
        frontLeft.set(left);
        rearRight.set(right);
        frontRight.set(right);

    }

    protected void initDefaultCommand() {

    }

}
