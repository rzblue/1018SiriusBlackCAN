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
    public RobotConfig.DrivetrainConfig CONFIG = RobotConfig.DRIVETRAIN_CONFIG;

    private static Drivetrain instance;

    private TalonSRX rearRight = new TalonSRX(CONFIG.MOTOR_REAR_RIGHT_PWM);
    private TalonSRX rearLeft = new TalonSRX(CONFIG.MOTOR_REAR_LEFT_PWM);
    private TalonSRX frontRight = new TalonSRX(CONFIG.MOTOR_FRONT_RIGHT_PWM);
    private TalonSRX frontLeft = new TalonSRX(CONFIG.MOTOR_FRONT_LEFT_PWM);

    private Encoder rightEncoder = new Encoder(CONFIG.ENCODER_RIGHT_A_DIO, CONFIG.ENCODER_RIGHT_B_DIO, CONFIG.RIGHT_ENCODER_REVERSE_CFG);
    private Encoder leftEncoder = new Encoder(CONFIG.ENCODER_LEFT_A_DIO, CONFIG.ENCODER_LEFT_B_DIO, CONFIG.LEFT_ENCODER_REVERSE_CFG);

    private AHRS navX = new AHRS(CONFIG.NAVX_SPI);

    private RobotDrive driveHelper = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

    private boolean fieldOriented = CONFIG.FIELD_ORIENTED_CFG;

    private Drivetrain() {
        rearRight.setInverted(true);
        frontRight.setInverted(true);
        rightEncoder.reset();
        leftEncoder.reset();
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

    public int getLeftEncoderTicks() {
        return leftEncoder.get();
    }

    public int getRightEncoderTicks() {
        return rightEncoder.get();
    }

    public void resetLeftEncoder() {
        leftEncoder.reset();
    }

    public void resetRightEncoder() {
        rightEncoder.reset();
    }

    public void resetEncoders() {
        resetLeftEncoder();
        resetRightEncoder();
    }

    public double getYaw() {
        return navX.getYaw();
    }

    protected void initDefaultCommand() {

    }

}
