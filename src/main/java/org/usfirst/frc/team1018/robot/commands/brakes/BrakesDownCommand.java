package org.usfirst.frc.team1018.robot.commands.brakes;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Brakes;

/**
 * @author Ryan Blue
 */
public class BrakesDownCommand extends BasicCommand {
    private Brakes brakes = Brakes.getInstance();

    public BrakesDownCommand() {
        requires(brakes);
    }

    protected void runOnce() {
        brakes.set();
    }

    protected void end() {}
}
