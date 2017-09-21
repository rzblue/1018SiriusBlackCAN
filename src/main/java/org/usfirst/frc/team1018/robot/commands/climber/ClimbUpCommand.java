package org.usfirst.frc.team1018.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1018.lib.BasicCommand;
import org.usfirst.frc.team1018.robot.subsystems.Climber;

/**
 * @author Ryan Blue
 */
public class ClimbUpCommand extends Command {
    private Climber climber = Climber.getInstance();

    public ClimbUpCommand() {
        requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        climber.climbUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished() returns true
    protected void end() {
        climber.stop();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
