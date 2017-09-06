package org.usfirst.frc.team1018.robot.commands;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Climber;

/**
 * @author Ryan Blue
 */
public class ClimbUpCommand extends BasicCommand {
    Climber climber = Climber.getInstance();

    public ClimbUpCommand() {
        requires(climber);
    }

    protected void runOnce() {
        climber.climbUp();
    }

    protected void end() {
        climber.stop();
    }
}
