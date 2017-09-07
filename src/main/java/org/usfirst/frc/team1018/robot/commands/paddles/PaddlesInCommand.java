package org.usfirst.frc.team1018.robot.commands.paddles;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Paddles;

/**
 * @author Ryan Blue
 */
public class PaddlesInCommand extends BasicCommand {
    Paddles paddles = Paddles.getInstance();

    public PaddlesInCommand() {
        requires(paddles);
    }

    protected void runOnce() {
        paddles.paddlesIn();
    }

    protected void end() {}
}
