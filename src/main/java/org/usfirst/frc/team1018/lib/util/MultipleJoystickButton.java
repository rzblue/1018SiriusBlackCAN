package org.usfirst.frc.team1018.lib.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Ryan Blue
 */
public class MultipleJoystickButton extends Button {
    private JoystickButton[] buttons;

    public MultipleJoystickButton(Joystick joystick, int... buttonNumbers) {
        for(int buttonNumber : buttonNumbers) {
            buttons[buttons.length] = new JoystickButton(joystick, buttonNumber);
        }
    }

    public MultipleJoystickButton(JoystickButton... buttons) {
        this.buttons = buttons;
    }

    @Override
    public boolean get() {
        for(JoystickButton b : buttons) if(!b.get()) return false;
        return true;
    }
}
