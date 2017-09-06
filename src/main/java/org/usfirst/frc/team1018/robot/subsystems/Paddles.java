package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotConfig;
import org.usfirst.frc.team1018.robot.commands.PaddlesOutCommand;

/**
 * @author Ryan Blue
 */
public class Paddles extends Subsystem {
    private static Paddles instance;

    private DoubleSolenoid paddles = new DoubleSolenoid(RobotConfig.PaddlesConfig.PADDLES_FOR_SOL, RobotConfig.PaddlesConfig.PADDLES_REV_SOL);

    private Paddles() {
    }

    public static Paddles getInstance() {
        if(instance == null) instance = new Paddles();
        return instance;
    }

    public void paddlesOut() {
        paddles.set(DoubleSolenoid.Value.kForward);
    }

    public void paddlesIn() {
        paddles.set(DoubleSolenoid.Value.kReverse);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new PaddlesOutCommand());
    }
}

