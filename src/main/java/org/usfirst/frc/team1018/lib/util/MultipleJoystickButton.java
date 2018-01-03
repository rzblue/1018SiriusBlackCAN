package org.usfirst.frc.team1018.lib.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Ryan Blue
 * A {@link Button} that gets its state from multiple {@link JoystickButton}.
 * <p>
 * Allows for button combinations on a control panel.
 */
public class MultipleJoystickButton extends Button {
    private final GenericHID joystick;
    int[] buttonNumbers;

    /**
     * @param joystick      The GenericHID object that has the buttons (e.g. Joystick, KinectStick,
     *                      etc)
     * @param buttonNumbers The button numbers (see {@link GenericHID#getRawButton(int) }
     */
    public MultipleJoystickButton(GenericHID joystick, int... buttonNumbers) {
        this.joystick = joystick;
        this.buttonNumbers = buttonNumbers;
    }

    @Override
    public boolean get() {
        for(int buttonNumber : buttonNumbers) if(!joystick.getRawButton(buttonNumber)) return false;
        return true;
    }
}
