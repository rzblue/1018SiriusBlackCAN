package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team1018.lib.util.MultipleJoystickButton;
import org.usfirst.frc.team1018.robot.commands.brakes.SetBrakesCommand;
import org.usfirst.frc.team1018.robot.commands.drivetrain.DrivetrainBrakesCommand;
import org.usfirst.frc.team1018.robot.commands.climber.ClimbCommand;
import org.usfirst.frc.team1018.robot.commands.gearholder.RotateGearOverrideCommand;
import org.usfirst.frc.team1018.robot.commands.paddles.PaddlesInCommand;
import org.usfirst.frc.team1018.robot.subsystems.Climber;

/**
 * @author Ryan Blue
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private static OI instance;

    private Joystick leftStick = new Joystick(0);
    private Joystick rightStick = new Joystick(1);
    private Joystick buttonPanel = new Joystick(2);

    private JoystickButton dtBrakesButton = new JoystickButton(rightStick, 1);
    private MultipleJoystickButton climbDownButton = new MultipleJoystickButton(buttonPanel, 1, 11);
    private MultipleJoystickButton climbUpButton = new MultipleJoystickButton(buttonPanel, 2, 16);
    private MultipleJoystickButton paddlesInButton = new MultipleJoystickButton(buttonPanel, 2, 11);
    private JoystickButton brakesButton = new JoystickButton(buttonPanel, 10);
    private JoystickButton gearRotatorButton = new JoystickButton(buttonPanel, 15);

    private OI() {
        dtBrakesButton.whileHeld(new DrivetrainBrakesCommand(true));
        climbUpButton.whileHeld(new ClimbCommand(Climber.State.CLIMB_UP));
        climbDownButton.whileHeld(new ClimbCommand(Climber.State.CLIMB_DOWN));
        paddlesInButton.whileHeld(new PaddlesInCommand());
        brakesButton.whileHeld(new SetBrakesCommand(true));
        gearRotatorButton.whileHeld(new RotateGearOverrideCommand());
    }

    public static OI getInstance() {
        if(instance == null) instance = new OI();
        return instance;
    }

    public double getX() {
        return leftStick.getX();
    }

    public double getY() {
        return leftStick.getY();
    }

    public double getTurn() {
        return rightStick.getX();
    }

    public boolean getAlignButton() {
        return leftStick.getRawButton(1);
    }

}
