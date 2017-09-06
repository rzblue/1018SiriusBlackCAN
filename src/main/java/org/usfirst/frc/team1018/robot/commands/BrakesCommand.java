package org.usfirst.frc.team1018.robot.commands;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Brakes;

/**
 * @author Ryan Blue
 */
public class BrakesCommand extends BasicCommand {
    private Brakes brakes = Brakes.getInstance();

    public BrakesCommand() {
        requires(brakes);
    }

    protected void runOnce() {
        brakes.set();
    }

    protected void end() {
        brakes.release();
    }
}
