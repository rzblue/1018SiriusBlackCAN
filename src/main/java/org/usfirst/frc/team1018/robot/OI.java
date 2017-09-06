package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Ryan Blue
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick leftStick = new Joystick(0);
    private Joystick rightStick = new Joystick(1);
    private Joystick buttonPanel = new Joystick(2);

    public double getX() {
        return leftStick.getX();
    }

    public double getY() {
        return leftStick.getY();
    }

    public double getTurn() {
        return rightStick.getX();
    }

}
