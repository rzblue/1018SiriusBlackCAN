package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1018.robot.RobotConfig;
import org.usfirst.frc.team1018.robot.commands.paddles.PaddlesOutCommand;

/**
 * @author Ryan Blue
 */
public class Paddles extends Subsystem {
    private static Paddles instance;
    public RobotConfig.PaddlesConfig CONFIG = RobotConfig.PADDLES_CONFIG;
    private DoubleSolenoid paddles = new DoubleSolenoid(CONFIG.PADDLES_FOR_SOL, CONFIG.PADDLES_REV_SOL);

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

    public void outputToSmartDashboard() {
        SmartDashboard.putString("Paddle State: ", paddles.get() == DoubleSolenoid.Value.kForward ? "Paddles Out" : "Paddles In");
    }

    public void initDefaultCommand() {
        setDefaultCommand(new PaddlesOutCommand());
    }
}

