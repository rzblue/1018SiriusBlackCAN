package org.usfirst.frc.team1018.robot.commands;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Brakes;

/**
 * @author Ryan Blue
 */
public class BrakesUpCommand extends BasicCommand {
    Brakes brakes = Brakes.getInstance();

    public BrakesUpCommand() {
        requires(brakes);
    }

    protected void runOnce() {
        brakes.release();
    }

    protected void end() {}
}
