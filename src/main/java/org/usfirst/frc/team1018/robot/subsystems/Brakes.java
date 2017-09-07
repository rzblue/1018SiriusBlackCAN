package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotConfig;
import org.usfirst.frc.team1018.robot.commands.BrakesUpCommand;

/**
 * @author Ryan Blue
 */
public class Brakes extends Subsystem {
    private static Brakes instance;

    private DoubleSolenoid brakesSolenoid = new DoubleSolenoid(RobotConfig.BrakesConfig.BRAKES_FOR_SOL, RobotConfig.BrakesConfig.BRAKES_REV_SOL);

    private Brakes() {
        release();
    }

    public void release() {
        brakesSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public static Brakes getInstance() {
        if(instance == null) instance = new Brakes();
        return instance;
    }

    public void set() {
        brakesSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new BrakesUpCommand());
    }
}

