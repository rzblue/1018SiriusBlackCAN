package org.usfirst.frc.team1018.robot.commands;

import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Climber;

/**
 * @author Ryan Blue
 */
public class ClimbDownCommand extends BasicCommand {
    private Climber climber = Climber.getInstance();

    public ClimbDownCommand() {
        requires(climber);
    }

    protected void runOnce() {
        climber.climbDown();
    }

    protected void end() {
        climber.stop();
    }
}
