package org.usfirst.frc.team1018.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1018.robot.subsystems.Climber;

/**
 * @author Ryan Blue
 * <p>
 * Runs the climber in reverse
 * @see Climber
 */
public class ClimbCommand extends Command {
    private Climber climber = Climber.getInstance();
    private Climber.State state;

    public ClimbCommand(Climber.State state) {
        requires(climber);
        this.state = state;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        climber.setState(state);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished() returns true
    @Override
    protected void end() {
        climber.setState(Climber.State.OFF);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
