package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1018.robot.RobotConfig;
import org.usfirst.frc.team1018.robot.commands.brakes.SetBrakesCommand;

/**
 * @author Ryan Blue
 * <p>
 * Represents the Brakes subsystem
 */
public class Brakes extends Subsystem {
    private static Brakes instance;
    public RobotConfig.BrakesConfig CONFIG = RobotConfig.BRAKES_CONFIG;
    private DoubleSolenoid brakesSolenoid = new DoubleSolenoid(CONFIG.BRAKES_FOR_SOL, CONFIG.BRAKES_REV_SOL);

    private Brakes() {
        setDown(false);
    }

    public static Brakes getInstance() {
        if(instance == null) instance = new Brakes();
        return instance;
    }

    /**
     * Sets the brakes
     */
    public void setDown(boolean down) {
        brakesSolenoid.set(down ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new SetBrakesCommand(false));
    }

    public void outputToSmartDashboard() {
        SmartDashboard.putString("Brakes State: ", brakesSolenoid.get() == DoubleSolenoid.Value.kForward ? "On" : "Off");
    }
}

