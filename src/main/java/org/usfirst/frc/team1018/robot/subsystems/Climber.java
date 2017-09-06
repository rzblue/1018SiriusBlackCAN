package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotConfig;

/**
 * @author Ryan Blue
 */
public class Climber extends Subsystem {
    private static Climber instance;

    private TalonSRX lowerClimber = new TalonSRX(RobotConfig.ClimberConfig.CLIMBER_LOWER_PWM);
    private TalonSRX upperClimber = new TalonSRX(RobotConfig.ClimberConfig.CLIMBER_UPPER_PWM);

    private Climber() {
        lowerClimber.setInverted(true);
    }

    public static Climber getInstance() {
        if(instance == null) instance = new Climber();
        return instance;
    }

    public void climbUp() {
        lowerClimber.set(1);
        upperClimber.set(1);
    }

    public void climbDown() {
        lowerClimber.set(-1);
        upperClimber.set(-1);
    }

    public void stop() {
        lowerClimber.set(0);
        lowerClimber.set(0);
    }

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}

