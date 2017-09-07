package org.usfirst.frc.team1018.robot.commands.paddles;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Paddles;

/**
 * @author Ryan Blue
 */
public class PaddlesOutCommand extends BasicCommand {
    Paddles paddles = Paddles.getInstance();

    public PaddlesOutCommand() {
        requires(paddles);
    }

    protected void runOnce() {
        paddles.paddlesOut();
    }

    protected void end() {}
}
